// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelEvilEye extends EntityModel {
	private final ModelRenderer head;
	private final ModelRenderer body;

	public ModelEvilEye() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 8, -2.0F, -22.0F, -2.0F, 4, 4, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, 6.0F, -1.0F);
		body.cubeList.add(new ModelBox(body, 0, 4, -6.0F, 4.0F, -6.0F, 14, 14, 14, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
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
	}
}