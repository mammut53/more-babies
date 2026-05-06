// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class WardenModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "wardenmodel"), "main");
	private final ModelPart bone;
	private final ModelPart body;
	private final ModelPart right_ribcage;
	private final ModelPart left_ribcage;
	private final ModelPart head;
	private final ModelPart right_tendril;
	private final ModelPart left_tendril;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public WardenModel(ModelPart root) {
		this.bone = root.getChild("bone");
		this.body = this.bone.getChild("body");
		this.right_ribcage = this.body.getChild("right_ribcage");
		this.left_ribcage = this.body.getChild("left_ribcage");
		this.head = this.body.getChild("head");
		this.right_tendril = this.head.getChild("right_tendril");
		this.left_tendril = this.head.getChild("left_tendril");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.right_leg = this.bone.getChild("right_leg");
		this.left_leg = this.bone.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -5.75F, -2.25F, 9.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.5F, 0.0F));

		PartDefinition right_ribcage = body.addOrReplaceChild("right_ribcage", CubeListBuilder.create().texOffs(30, 6).addBox(-1.0F, -5.25F, -0.3F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -0.5F, -2.0F));

		PartDefinition left_ribcage = body.addOrReplaceChild("left_ribcage", CubeListBuilder.create().texOffs(30, 6).mirror().addBox(-3.0F, -5.25F, -0.3F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.5F, -0.5F, -2.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 44).addBox(-6.0F, -12.0F, -4.0F, 12.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.75F, 0.75F));

		PartDefinition right_tendril = head.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(40, 0).addBox(-12.0F, -10.0F, 0.0F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -9.0F, 0.0F));

		PartDefinition left_tendril = head.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(0.0F, -10.0F, 0.0F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, -9.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 29).addBox(-2.0F, 0.25F, -1.75F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -6.0F, 0.5F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(22, 25).addBox(-2.0F, 0.25F, -1.75F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -6.0F, 0.5F));

		PartDefinition right_leg = bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 33).addBox(-1.55F, 0.25F, -1.25F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.95F, -6.5F, 0.0F));

		PartDefinition left_leg = bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(38, 24).addBox(-1.45F, 0.25F, -1.25F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.95F, -6.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}