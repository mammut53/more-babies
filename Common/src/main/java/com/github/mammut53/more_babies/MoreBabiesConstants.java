package com.github.mammut53.more_babies;

import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MoreBabiesConstants {

	public static final String MOD_ID = "more_babies";
	public static final String MOD_NAME = "More Babies";
	public static final Logger LOG = LogManager.getLogger(MOD_NAME);

	public static final List<String> BABY_IDS = Lists.newArrayList(
			"bat",
			"blaze",
			"cave_spider",
			"creeper",
			"dolphin",
			"elder_guardian",
			//"ender_dragon",
			"enderman",
			"evoker",
			"ghast",
			"giant",
			"glow_squid",
			"guardian",
			"illusioner",
			"iron_golem",
			"parrot",
			"piglin_brute",
			"pillager",
			"ravager",
			"salmon",
			"shulker",
			"skeleton",
			"snow_golem",
			"spider",
			"squid",
			"stray",
			"vindicator",
			"wandering_trader",
			//"warden",
			"witch",
			"wither",
			"wither_skeleton"
	);
}