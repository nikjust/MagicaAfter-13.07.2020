// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelslug extends EntityModel {
	private final ModelRenderer head;
	private final ModelRenderer bb_main;

	public Modelslug() {
		textureWidth = 16;
		textureHeight = 16;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, -7.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -2.0F, -5.0F, -2.0F, 4, 3, 2, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 6, -2.0F, -4.0F, -7.0F, 4, 4, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 9, -2.0F, -3.0F, -1.0F, 4, 3, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 12, -1.0F, -2.0F, 3.0F, 2, 2, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 1, 13, -1.0F, -1.0F, 5.0F, 2, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		bb_main.render(f5);
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