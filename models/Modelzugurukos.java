// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelzugurukos extends EntityModel {
	private final ModelRenderer head;
	private final ModelRenderer armL;
	private final ModelRenderer armR;
	private final ModelRenderer LegL;
	private final ModelRenderer LegR;
	private final ModelRenderer Body;

	public Modelzugurukos() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.4F, -2.4F);
		head.cubeList.add(new ModelBox(head, 36, 4, -3.0F, -4.4F, -0.6F, 6, 6, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 1, 59, -4.0F, -3.4F, -1.6F, 3, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 1, 59, 1.0F, -3.4F, -1.6F, 3, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 19, 41, -2.0F, -0.4F, -0.6F, 1, 4, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 22, 45, 1.0F, -0.4F, -0.6F, 1, 4, 1, 0.0F, false));

		armL = new ModelRenderer(this);
		armL.setRotationPoint(6.0F, 3.5F, 0.0F);
		armL.cubeList.add(new ModelBox(armL, 17, 16, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));

		armR = new ModelRenderer(this);
		armR.setRotationPoint(-6.0F, 3.5F, 0.0F);
		armR.cubeList.add(new ModelBox(armR, 1, 16, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(2.0F, 14.5F, 0.0F);
		LegL.cubeList.add(new ModelBox(LegL, 17, 1, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(-2.0F, 14.5F, 0.0F);
		LegR.cubeList.add(new ModelBox(LegR, 1, 1, -2.0F, -1.5F, -2.0F, 4, 11, 4, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 7.5F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 36, 40, -4.0F, -5.5F, -2.0F, 8, 11, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		armL.render(f5);
		armR.render(f5);
		LegL.render(f5);
		LegR.render(f5);
		Body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.armR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}