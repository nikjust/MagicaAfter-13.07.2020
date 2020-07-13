// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelCollosusHeart extends EntityModel {
	private final ModelRenderer Q;
	private final ModelRenderer f;
	private final ModelRenderer bone;

	public ModelCollosusHeart() {
		textureWidth = 32;
		textureHeight = 32;

		Q = new ModelRenderer(this);
		Q.setRotationPoint(-8.0F, 16.0F, 8.0F);
		Q.cubeList.add(new ModelBox(Q, 0, 2, -3.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F, false));

		f = new ModelRenderer(this);
		f.setRotationPoint(0.0F, 0.0F, 0.0F);
		Q.addChild(f);
		f.cubeList.add(new ModelBox(f, 0, 4, -3.0316F, -1.9065F, -1.0F, 2, 2, 1, 0.0F, false));
		f.cubeList.add(new ModelBox(f, 0, 2, -3.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 6, -12.2367F, -11.1355F, 8.0F, 2, 4, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 6, -11.2367F, -9.4355F, 8.0F, 2, 4, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 4, -13.2367F, -11.1355F, 8.5F, 1, 3, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 6, -10.0F, -11.0F, 8.0F, 3, 5, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 6, -10.8F, -10.6F, 8.0F, 3, 5, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 2, -11.0F, -14.0F, 10.0F, 1, 3, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 1, -11.0F, -14.0F, 9.0F, 1, 3, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 7, -9.0F, -11.0F, 10.0F, 3, 1, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 7, -9.0F, -10.4F, 9.4F, 3, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 7, -9.0F, -9.8F, 10.0F, 3, 0, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 3, -12.0F, -14.0F, 9.0F, 0, 3, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Q.render(f5);
		bone.render(f5);
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