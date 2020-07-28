// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelEnderManCultist extends EntityModel {
	private final ModelRenderer ArmL;
	private final ModelRenderer ArmR;
	private final ModelRenderer LegL;
	private final ModelRenderer LegR;
	private final ModelRenderer Head;
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer Body;

	public ModelEnderManCultist() {
		textureWidth = 128;
		textureHeight = 128;

		ArmL = new ModelRenderer(this);
		ArmL.setRotationPoint(4.0F, -15.0F, 0.0F);
		ArmL.cubeList.add(new ModelBox(ArmL, 118, 2, -1.0F, -1.0F, -1.0F, 2, 26, 2, 0.0F, false));

		ArmR = new ModelRenderer(this);
		ArmR.setRotationPoint(-4.0F, -15.0F, 0.0F);
		ArmR.cubeList.add(new ModelBox(ArmR, 110, 2, -1.0F, -1.0F, -1.0F, 2, 26, 2, 0.0F, false));

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(1.0F, 5.0F, 0.0F);
		LegL.cubeList.add(new ModelBox(LegL, 85, 8, -1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F, false));

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(-1.0F, 5.0F, 0.0F);
		LegR.cubeList.add(new ModelBox(LegR, 111, 31, -1.0F, -1.0F, -1.0F, 2, 20, 2, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, -19.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 86, 25, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, -4.0F);
		Head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 74, 24, -1.9094F, 0.0F, 0.0423F, 4, 3, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-2.0F, -2.5F, -3.5F);
		Head.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.4363F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 74, 10, -1.9094F, -2.5F, -0.4577F, 4, 5, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(1.4F, -2.5F, -3.5F);
		Head.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 74, 17, -2.0F, -2.5F, -0.5F, 4, 5, 1, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, -6.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 94, 2, -3.0F, -10.0F, -1.0F, 6, 20, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		ArmL.render(f5);
		ArmR.render(f5);
		LegL.render(f5);
		LegR.render(f5);
		Head.render(f5);
		Body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.ArmR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.ArmL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}