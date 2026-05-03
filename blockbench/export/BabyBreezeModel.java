// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class BreezeModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "breezemodel"), "main");
	private final ModelPart body;
	private final ModelPart rods;
	private final ModelPart rod_1;
	private final ModelPart rod_2;
	private final ModelPart rod_3;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart wind_body;
	private final ModelPart wind_bottom;
	private final ModelPart wind_mid;
	private final ModelPart wind_top;

	public BreezeModel(ModelPart root) {
		this.body = root.getChild("body");
		this.rods = this.body.getChild("rods");
		this.rod_1 = this.rods.getChild("rod_1");
		this.rod_2 = this.rods.getChild("rod_2");
		this.rod_3 = this.rods.getChild("rod_3");
		this.head = this.body.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.wind_body = root.getChild("wind_body");
		this.wind_bottom = this.wind_body.getChild("wind_bottom");
		this.wind_mid = this.wind_bottom.getChild("wind_mid");
		this.wind_top = this.wind_mid.getChild("wind_top");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition rods = body.addOrReplaceChild("rods", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition rod_1 = rods.addOrReplaceChild("rod_1", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.2991F, -1.5F, 0.75F, -2.7489F, -1.0472F, 3.1416F));

		PartDefinition rod_2 = rods.addOrReplaceChild("rod_2", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2991F, -1.5F, 0.75F, -2.7489F, 1.0472F, 3.1416F));

		PartDefinition rod_3 = rods.addOrReplaceChild("rod_3", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, -1.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(2, 18).addBox(-4.0F, -3.875F, -3.15F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(2, 18).addBox(-4.0F, -3.875F, -3.15F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wind_body = partdefinition.addOrReplaceChild("wind_body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition wind_bottom = wind_body.addOrReplaceChild("wind_bottom", CubeListBuilder.create().texOffs(0, 41).addBox(-1.0F, -3.75F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition wind_mid = wind_bottom.addOrReplaceChild("wind_mid", CubeListBuilder.create().texOffs(38, 14).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(41, 16).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(21, 36).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 0.0F));

		PartDefinition wind_top = wind_mid.addOrReplaceChild("wind_top", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4.0F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(3, 3).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(53, 28).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wind_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}