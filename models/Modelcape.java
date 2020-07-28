// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelcape extends EntityModel {
	private final ModelRenderer leggins;

	public Modelcape() {
		textureWidth = 64;
		textureHeight = 64;

		leggins = new ModelRenderer(this);
		leggins.setRotationPoint(0.0F, 12.0F, 2.5F);
		setRotationAngle(leggins, 0.3054F, 0.0F, 0.0F);
		leggins.cubeList.add(new ModelBox(leggins, 0, 0, -4.0F, 0.0F, -0.5F, 8, 12, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leggins.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.leggins.rotateAngleX = f2 / 20.f;
	}
}