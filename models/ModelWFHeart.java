// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelWFHeart extends EntityModel {
	private final ModelRenderer Rotator;
	private final ModelRenderer all;

	public ModelWFHeart() {
		textureWidth = 16;
		textureHeight = 16;

		Rotator = new ModelRenderer(this);
		Rotator.setRotationPoint(9.0F, 0.0F, 1.0F);
		Rotator.cubeList.add(new ModelBox(Rotator, 0, 0, -9.0F, -20.0F, -20.0F, 16, 40, 40, 0.0F, false));

		all = new ModelRenderer(this);
		all.setRotationPoint(8.0F, 0.0F, 1.0F);
		all.cubeList.add(new ModelBox(all, 0, 0, -24.0F, -22.0F, 7.0F, 39, 40, 16, 0.0F, false));
		all.cubeList.add(new ModelBox(all, 0, 0, -12.0F, -39.0F, -11.0F, 11, 32, 11, 0.0F, false));
		all.cubeList.add(new ModelBox(all, 0, 0, 2.0F, -39.0F, -17.0F, 11, 32, 11, 0.0F, false));
		all.cubeList.add(new ModelBox(all, 0, 0, 2.0F, -46.0F, -4.0F, 11, 32, 11, 0.0F, false));
		all.cubeList.add(new ModelBox(all, 0, 0, -24.0F, -22.0F, -24.0F, 39, 32, 16, 0.0F, false));
		all.cubeList.add(new ModelBox(all, 0, 0, -32.0F, -24.0F, -9.0F, 48, 48, 16, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Rotator.render(f5);
		all.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Rotator.rotateAngleX = f2 / 20.f;
	}
}