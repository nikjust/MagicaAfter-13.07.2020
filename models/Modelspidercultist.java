// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelspidercultist extends EntityModel {
	private final ModelRenderer armL;
	private final ModelRenderer ArmR;
	private final ModelRenderer ArmL1;
	private final ModelRenderer ArmR1;
	private final ModelRenderer ArmL2;
	private final ModelRenderer ArmR2;
	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer LegR;
	private final ModelRenderer LegL;

	public Modelspidercultist() {
		textureWidth = 128;
		textureHeight = 128;

		armL = new ModelRenderer(this);
		armL.setRotationPoint(-5.0F, 5.0F, 0.0F);
		armL.cubeList.add(new ModelBox(armL, 8, 14, -1.0F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));

		ArmR = new ModelRenderer(this);
		ArmR.setRotationPoint(5.0F, 5.0F, 0.0F);
		ArmR.cubeList.add(new ModelBox(ArmR, 0, 14, -1.0F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));

		ArmL1 = new ModelRenderer(this);
		ArmL1.setRotationPoint(-2.25F, 5.0F, -3.0F);
		ArmL1.cubeList.add(new ModelBox(ArmL1, 57, 0, -3.75F, -1.0F, -1.0F, 5, 2, 2, 0.0F, false));
		ArmL1.cubeList.add(new ModelBox(ArmL1, 48, 15, -5.75F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));

		ArmR1 = new ModelRenderer(this);
		ArmR1.setRotationPoint(2.25F, 5.0F, -3.0F);
		ArmR1.cubeList.add(new ModelBox(ArmR1, 39, 15, 3.75F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
		ArmR1.cubeList.add(new ModelBox(ArmR1, 58, 4, -1.25F, -1.0F, -1.0F, 5, 2, 2, 0.0F, false));

		ArmL2 = new ModelRenderer(this);
		ArmL2.setRotationPoint(-0.5F, 9.0F, -5.0F);
		ArmL2.cubeList.add(new ModelBox(ArmL2, 20, 26, -7.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F, false));
		ArmL2.cubeList.add(new ModelBox(ArmL2, 0, 32, -9.5F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));

		ArmR2 = new ModelRenderer(this);
		ArmR2.setRotationPoint(1.5F, 9.0F, -5.0F);
		ArmR2.cubeList.add(new ModelBox(ArmR2, 8, 31, 6.5F, -1.0F, -1.0F, 2, 10, 2, 0.0F, false));
		ArmR2.cubeList.add(new ModelBox(ArmR2, 0, 27, -1.5F, -1.0F, -1.0F, 8, 2, 2, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 9.0F, -1.5F);
		Body.cubeList.add(new ModelBox(Body, 33, 0, -4.0F, -5.0F, -0.5F, 8, 10, 4, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 16, 32, -1.0F, -1.0F, -2.5F, 2, 2, 2, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.05F, 1.5F, 1.25F);
		Head.cubeList.add(new ModelBox(Head, 15, 15, -2.45F, -4.5F, -3.25F, 5, 7, 4, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 34, 15, -0.55F, 0.0F, 0.75F, 1, 2, 1, 0.0F, false));

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(2.0F, 15.0F, 0.0F);
		LegR.cubeList.add(new ModelBox(LegR, 16, 0, -2.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F, false));

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(-2.0F, 15.0F, 0.0F);
		LegL.cubeList.add(new ModelBox(LegL, 0, 0, -2.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		armL.render(f5);
		ArmR.render(f5);
		ArmL1.render(f5);
		ArmR1.render(f5);
		ArmL2.render(f5);
		ArmR2.render(f5);
		Body.render(f5);
		Head.render(f5);
		LegR.render(f5);
		LegL.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.ArmR1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.ArmR2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.ArmL1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.ArmR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.ArmL2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}