// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelEvilSkull extends EntityModel {
	private final ModelRenderer chelust;
	private final ModelRenderer Body;

	public ModelEvilSkull() {
		textureWidth = 128;
		textureHeight = 128;

		chelust = new ModelRenderer(this);
		chelust.setRotationPoint(0.0F, 21.0F, 6.8333F);
		chelust.cubeList.add(new ModelBox(chelust, 52, 19, -8.0F, -3.0F, -15.8333F, 16, 6, 1, 0.0F, false));
		chelust.cubeList.add(new ModelBox(chelust, 11, 6, 8.0F, -3.0F, -15.8333F, 1, 6, 17, 0.0F, false));
		chelust.cubeList.add(new ModelBox(chelust, 10, 6, -9.0F, -3.0F, -15.8333F, 1, 6, 17, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 11.3F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 87, 2, -9.0F, -5.3F, -8.0F, 1, 12, 16, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 87, 3, 8.0F, -5.3F, -8.0F, 1, 12, 16, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 20, 41, -9.0F, -6.3F, -9.0F, 18, 1, 18, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 33, 73, -9.0F, -5.3F, 8.0F, 18, 18, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 48, 5, -9.0F, -5.3F, -9.0F, 18, 12, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		chelust.render(f5);
		Body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.chelust.rotateAngleZ = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}