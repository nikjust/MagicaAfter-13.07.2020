// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelbullet extends EntityModel {
	private final ModelRenderer bullet;

	public Modelbullet() {
		textureWidth = 256;
		textureHeight = 256;

		bullet = new ModelRenderer(this);
		bullet.setRotationPoint(-20.0F, 10.0F, 8.0F);
		bullet.cubeList.add(new ModelBox(bullet, 0, 2, 6.0F, 6.0F, -1.0F, 2, 2, 2, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 0, 2, 16.0F, 6.0F, -1.0F, 2, 2, 2, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 0, 2, 11.0F, 11.0F, -1.0F, 2, 2, 2, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 0, 2, 11.0F, 1.0F, -1.0F, 2, 2, 2, 0.0F, false));
		bullet.cubeList.add(new ModelBox(bullet, 93, 0, 11.0F, 6.0F, -8.0F, 2, 2, 16, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bullet.render(f5);
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