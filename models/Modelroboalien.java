// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
public static class Modelroboalien extends AnimatedEntityModel<Entity> {

	private final AnimatedModelRenderer legl;
	private final AnimatedModelRenderer legr;
	private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer armr;
	private final AnimatedModelRenderer arml;

	public Modelroboalien() {
		textureWidth = 16;
		textureHeight = 16;
		legl = new AnimatedModelRenderer(this);
		legl.setRotationPoint(2.0F, 9.0F, 0.0F);
		legl.setTextureOffset(0, 0).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);
		legl.setModelRendererName("legl");
		this.registerModelRenderer(legl);

		legr = new AnimatedModelRenderer(this);
		legr.setRotationPoint(-2.0F, 9.0F, 0.0F);
		legr.setTextureOffset(0, 0).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);
		legr.setModelRendererName("legr");
		this.registerModelRenderer(legr);

		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, -6.5F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-4.0F, -8.5F, -2.0F, 8.0F, 17.0F, 4.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -15.0F, 3.0F);
		head.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		armr = new AnimatedModelRenderer(this);
		armr.setRotationPoint(6.0F, -13.0F, 0.0F);
		armr.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);
		armr.setModelRendererName("armr");
		this.registerModelRenderer(armr);

		arml = new AnimatedModelRenderer(this);
		arml.setRotationPoint(-6.0F, -13.0F, 0.0F);
		arml.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 22.0F, 4.0F, 0.0F, false);
		arml.setModelRendererName("arml");
		this.registerModelRenderer(arml);

		this.rootBones.add(legl);
		this.rootBones.add(legr);
		this.rootBones.add(body);
		this.rootBones.add(head);
		this.rootBones.add(armr);
		this.rootBones.add(arml);
	}

	@Override
	public ResourceLocation getAnimationFileLocation() {
		return new ResourceLocation("MODID", "animations/ANIMATIONFILE.json");
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.legr.rotateAngleZ = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.armr.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.armr.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.legl.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.arml.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.arml.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}