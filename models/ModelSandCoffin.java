// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelSandCoffin extends EntityModel {
	private final ModelRenderer body;

	public ModelSandCoffin() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -4.0F, -1.0F, 8, 8, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 10, -1.0F, -4.0F, -4.0F, 2, 8, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 20, 0, -4.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 22, 13, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}