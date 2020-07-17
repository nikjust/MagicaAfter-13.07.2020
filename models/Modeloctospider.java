// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modeloctospider extends EntityModel {
	private final ModelRenderer Body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer leg7;
	private final ModelRenderer leg8;

	public Modeloctospider() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 22.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 9, 0, -6.0F, -3.0F, -6.0F, 12, 3, 12, 0.0F, false));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-7.0F, 21.5F, 0.0F);
		setRotationAngle(leg1, 0.0F, 0.0F, -0.2618F);
		leg1.cubeList.add(new ModelBox(leg1, 0, 18, -8.0F, -0.5F, -1.0F, 9, 1, 2, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(5.7588F, 22.4659F, 0.0F);
		setRotationAngle(leg2, 0.0F, 0.0F, 0.2618F);
		leg2.cubeList.add(new ModelBox(leg2, 0, 18, -1.0176F, -1.4319F, -1.0F, 9, 1, 2, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-0.0544F, 22.087F, 4.5F);
		setRotationAngle(leg3, -0.2618F, 0.0F, 0.0F);
		leg3.cubeList.add(new ModelBox(leg3, 0, 21, -1.0F, -1.087F, -0.7588F, 2, 1, 9, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-0.0544F, 21.5F, -6.5F);
		setRotationAngle(leg4, 0.2618F, 0.0F, 0.0F);
		leg4.cubeList.add(new ModelBox(leg4, 0, 21, -1.0F, -0.5F, -7.7588F, 2, 1, 9, 0.0F, false));

		leg5 = new ModelRenderer(this);
		leg5.setRotationPoint(-6.0544F, 21.5F, -5.9F);
		setRotationAngle(leg5, 0.2618F, 0.7854F, 0.0F);
		leg5.cubeList.add(new ModelBox(leg5, 0, 21, -1.0F, -0.5F, -7.7588F, 2, 1, 9, 0.0F, false));

		leg6 = new ModelRenderer(this);
		leg6.setRotationPoint(5.0456F, 21.5F, -5.4F);
		setRotationAngle(leg6, 0.2618F, -0.7854F, 0.0F);
		leg6.cubeList.add(new ModelBox(leg6, 0, 21, -1.0F, -0.5F, -7.7588F, 2, 1, 9, 0.0F, false));

		leg7 = new ModelRenderer(this);
		leg7.setRotationPoint(-5.0F, 21.5F, 5.0F);
		setRotationAngle(leg7, 0.0F, 0.7854F, -0.2618F);
		leg7.cubeList.add(new ModelBox(leg7, 0, 18, -8.0F, -0.5F, -1.0F, 9, 1, 2, 0.0F, false));

		leg8 = new ModelRenderer(this);
		leg8.setRotationPoint(4.9456F, 22.087F, 5.5F);
		setRotationAngle(leg8, -0.2618F, 0.7854F, 0.0F);
		leg8.cubeList.add(new ModelBox(leg8, 0, 21, -1.0F, -1.087F, -0.7588F, 2, 1, 9, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		leg5.render(f5);
		leg6.render(f5);
		leg7.render(f5);
		leg8.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg5.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg8.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leg6.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leg7.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
	}
}