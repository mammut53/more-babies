// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class CreakingModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "creakingmodel"), "main");
	private final ModelPart root;
	private final ModelPart upper_body;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public CreakingModel(ModelPart root) {
		this.root = root.getChild("root");
		this.upper_body = this.root.getChild("upper_body");
		this.head = this.upper_body.getChild("head");
		this.body = this.upper_body.getChild("body");
		this.right_arm = this.upper_body.getChild("right_arm");
		this.left_arm = this.upper_body.getChild("left_arm");
		this.left_leg = this.root.getChild("left_leg");
		this.right_leg = this.root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition upper_body = root.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.6667F, -13.1665F, 0.0F));

		PartDefinition head = upper_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -8.7502F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(24, 21).addBox(-2.5F, -11.7502F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(8, 31).addBox(2.5F, -10.7502F, 0.0F, 8.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(24, 9).addBox(-10.5834F, -12.7502F, 0.0F, 8.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -8.0833F, 0.0F));

		PartDefinition body = upper_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -2.1668F, -2.3333F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-4.0F, -3.1668F, -2.3333F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.6666F, 0.6667F));

		PartDefinition right_arm = upper_body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 14).addBox(-1.3333F, -1.5002F, -1.6667F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-1.3333F, 12.4998F, -1.75F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.6666F, -6.3333F, 1.0F));

		PartDefinition left_arm = upper_body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 29).addBox(0.0F, -0.8335F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 11).addBox(0.0F, -3.75F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(40, 16).addBox(0.0F, 10.1665F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.9999F, 0.3333F));

		PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 29).addBox(-1.0F, -0.3334F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 35).addBox(-1.0F, 10.4666F, -3.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, -10.6666F, 0.3333F));

		PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -1.3334F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 29).addBox(-3.9999F, 11.4666F, -3.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(8, 27).addBox(-2.0F, -3.3334F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.6667F, -11.6666F, 0.3333F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}