// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelrune extends EntityModel {
	private final ModelRenderer TOP;
	private final ModelRenderer DOWN;

	public Modelrune() {
		textureWidth = 128;
		textureHeight = 128;

		TOP = new ModelRenderer(this);
		TOP.setRotationPoint(0.0F, 0.0F, 0.0F);
		TOP.cubeList.add(new ModelBox(TOP, 0, 0, -8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F, true));

		DOWN = new ModelRenderer(this);
		DOWN.setRotationPoint(0.0F, 0.0F, 0.0F);
		DOWN.cubeList.add(new ModelBox(DOWN, 0, 33, -8.0F, 8.0F, -8.0F, 16, 16, 16, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		TOP.render(f5);
		DOWN.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.DOWN.rotateAngleY = f2 / 20.f;
		this.TOP.rotateAngleY = f2;
	}
}