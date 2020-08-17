// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelVoidAvatar extends EntityModel {
	private final ModelRenderer Layer0;
	private final ModelRenderer Layer1;
	private final ModelRenderer Layer2;

	public ModelVoidAvatar() {
		textureWidth = 64;
		textureHeight = 64;

		Layer0 = new ModelRenderer(this);
		Layer0.setRotationPoint(0.0F, 16.0F, -0.3333F);
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));

		Layer1 = new ModelRenderer(this);
		Layer1.setRotationPoint(0.0F, 2.0F, -0.3333F);
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -20.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -20.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -20.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));

		Layer2 = new ModelRenderer(this);
		Layer2.setRotationPoint(0.0F, -12.0F, -0.3333F);
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Layer0.render(f5);
		Layer1.render(f5);
		Layer2.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Layer0.rotateAngleY = f2;
		this.Layer2.rotateAngleY = f2 / 20.f;
	}
}