// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class RavagerModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "ravagermodel"), "main");
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart mouth;
	private final ModelPart body;
	private final ModelPart right_hind_leg;
	private final ModelPart left_hind_leg;
	private final ModelPart right_front_leg;
	private final ModelPart left_front_leg;

	public RavagerModel(ModelPart root) {
		this.neck = root.getChild("neck");
		this.head = this.neck.getChild("head");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.mouth = this.head.getChild("mouth");
		this.body = root.getChild("body");
		this.right_hind_leg = root.getChild("right_hind_leg");
		this.left_hind_leg = root.getChild("left_hind_leg");
		this.right_front_leg = root.getChild("right_front_leg");
		this.left_front_leg = root.getChild("left_front_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(34, 50).addBox(-2.5F, -0.5F, -8.75F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.5F, 2.75F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -15.0F, -10.5F, 12.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.5F, -4.75F, -12.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -9.5F));

		PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(48, 32).addBox(-0.5F, -10.25F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -10.5F, -6.0F, 1.0996F, 0.0F, 0.0F));

		PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(48, 32).addBox(0.0F, -10.25F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -10.5F, -6.0F, 1.0996F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 27).addBox(-6.0F, 0.5F, -12.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 1.5F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 41).addBox(-3.5F, -5.0F, -3.5F, 7.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 59).addBox(-3.0F, 3.0F, -3.5F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.5F, 1.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_hind_leg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0F, -0.5F, -2.25F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 6.5F, 9.0F));

		PartDefinition left_hind_leg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0F, -0.5F, -2.25F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 6.5F, 9.0F));

		PartDefinition right_front_leg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, -0.5F, -2.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 6.5F, -2.5F));

		PartDefinition left_front_leg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, -0.5F, -2.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 6.5F, -2.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		neck.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_hind_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_hind_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_front_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_front_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}