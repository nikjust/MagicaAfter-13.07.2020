// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelcimex extends EntityModel {
	private final ModelRenderer RightLeg;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;
	private final ModelRenderer Body;

	public Modelcimex() {
		textureWidth = 128;
		textureHeight = 128;

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-9.0F, 15.0F, 0.0F);
		RightLeg.cubeList.add(new ModelBox(RightLeg, 110, 121, -1.0F, 8.0F, -4.0F, 3, 1, 6, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(9.0F, 3.3F, -0.9F);
		RightLeg.addChild(bone3);
		setRotationAngle(bone3, 1.5272F, 0.0F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 110, 121, -10.0F, -0.5F, -3.0F, 3, 1, 6, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(9.0F, 6.8F, -1.2F);
		RightLeg.addChild(bone4);
		setRotationAngle(bone4, 0.3927F, 0.0F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 110, 121, -10.0F, -0.5F, -3.0F, 3, 1, 6, 0.0F, false));

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(9.0F, 15.0F, 0.0F);
		LeftLeg.cubeList.add(new ModelBox(LeftLeg, 0, 121, -2.0F, 8.0F, -4.0F, 3, 1, 6, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-9.0F, 3.3F, -0.9F);
		LeftLeg.addChild(bone2);
		setRotationAngle(bone2, 1.5272F, 0.0F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 121, 7.0F, -0.5F, -3.0F, 3, 1, 6, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-9.0F, 6.8F, -1.2F);
		LeftLeg.addChild(bone);
		setRotationAngle(bone, 0.3927F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 121, 7.0F, -0.5F, -3.0F, 3, 1, 6, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(9.0F, 14.4F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 20, 0, -20.0F, -17.0F, -9.0F, 22, 18, 19, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		RightLeg.render(f5);
		LeftLeg.render(f5);
		Body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LeftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.RightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}