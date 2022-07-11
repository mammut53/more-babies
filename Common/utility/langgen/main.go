package main

import (
	"encoding/json"
	"flag"
	"io"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"strings"
)

var (
	translationKeysPath string
	enUsPath            string
	assetsUrl           string
	outputPath          string
)

func init() {
	flag.StringVar(&translationKeysPath, "translationKeysPath", "", "path to the translation keys file")
	flag.StringVar(&enUsPath, "enUs", "", "path to the en_us language file")
	flag.StringVar(&assetsUrl, "assetsUrl", "", "url to the Mojang assets object")
	flag.StringVar(&outputPath, "outputPath", "", "path to the language resource directory")

	flag.Parse()
}

func main() {

	var (
		translationKeys TranslationKeys
		translationMaps = make(map[string]map[string]string)
	)

	log.Printf("Reading translation keys")
	err := readJsonFile(translationKeysPath, &translationKeys)
	if err != nil {
		log.Fatalf("failed to read translation keys json file: %s", err)
	}

	log.Printf("Reading en_us file")
	var enUsMap map[string]string
	err = readJsonFile(enUsPath, &enUsMap)
	if err != nil {
		log.Fatalf("failed to read lanugage json file: %s", err)
	}
	translationMaps["en_us"] = enUsMap

	response, err := http.Get(assetsUrl)
	if err != nil {
		log.Fatalf("failed to get asset index: %s", err)
	}
	defer func(Body io.ReadCloser) {
		_ = Body.Close()
	}(response.Body)

	var index struct {
		Objects map[string]struct {
			Hash string
		}
	}

	err = json.NewDecoder(response.Body).Decode(&index)
	if err != nil {
		log.Fatalf("failed to decode asset index: %s", err)
	}

	for i, object := range index.Objects {
		prefix := "minecraft/lang/"
		if !strings.HasPrefix(i, prefix) {
			continue
		}
		name := i[len(prefix) : len(i)-len(".json")]

		log.Printf("Downloading: %s", name)
		resourceUrl := "https://resources.download.minecraft.net/" + object.Hash[:2] + "/" + object.Hash
		response, err := http.Get(resourceUrl)
		if err != nil {
			log.Fatalf("failed to get language resource: %s", err)
		}
		defer func(Body io.ReadCloser) {
			_ = Body.Close()
		}(response.Body)

		var translationMap map[string]string
		err = json.NewDecoder(response.Body).Decode(&translationMap)
		if err != nil {
			log.Fatalf("failed to decode language resource: %s", err)
		}

		translationMaps[name] = translationMap
	}

	for name, translationMap := range translationMaps {
		log.Print("Processing: " + name)

		var outputTranslationMap = make(map[string]string)
		for _, translationKey := range translationKeys {

			for _, translationName := range translationKey.KeyNames {
				translation := translationMap[translationKey.KeyOld.build()+translationName]
				outputTranslationMap[translationKey.KeyNew.build()+translationName] = translation
			}
		}

		log.Printf("Writing: %s", name)
		err := writeJsonFile(outputPath+name+".json", outputTranslationMap)
		if err != nil {
			log.Fatalf("failed to write lanugage json file: %s", err)
		}
	}
}

func readJsonFile(path string, v interface{}) error {
	file, err := os.Open(path)
	if err != nil {
		return err
	}

	bytes, err := ioutil.ReadAll(file)
	if err != nil {
		return err
	}

	err = json.Unmarshal(bytes, v)
	if err != nil {
		return err
	}

	return nil
}

func writeJsonFile(path string, v interface{}) error {
	bytes, err := json.MarshalIndent(&v, "", "  ")
	if err != nil {
		return err
	}

	err = ioutil.WriteFile(path, bytes, 0644)
	if err != nil {
		return err
	}

	return nil
}

type TranslationKeys []struct {
	KeyOld   Key      `json:"key_old"`
	KeyNew   Key      `json:"key_new"`
	KeyNames []string `json:"key_names"`
}

type Key struct {
	Prefix    string `json:"prefix"`
	Namespace string `json:"namespace"`
	Suffix    string `json:"suffix"`
}

func (k *Key) build() (key string) {
	key += k.Prefix
	if k.Prefix != "" {
		key += "."
	}

	key += k.Namespace
	if k.Namespace != "" {
		key += "."
	}

	key += k.Suffix
	if k.Suffix != "" {
		key += "."
	}
	return
}
