// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelVoidAvatarstage3 extends EntityModel {
	private final ModelRenderer Layer1;
	private final ModelRenderer Layer2;
	private final ModelRenderer Layer0;

	public ModelVoidAvatarstage3() {
		textureWidth = 64;
		textureHeight = 64;

		Layer1 = new ModelRenderer(this);
		Layer1.setRotationPoint(0.0F, 2.0F, -0.3333F);
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -10.0F, -6.0F, -13.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 2.0F, -16.0F, -17.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 4.0F, -14.0F, 4.3333F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -11.0F, -6.0F, 2.3333F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -16.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, 8.0F, -20.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -4.0F, -7.0F, -2.6667F, 12, 12, 12, 0.0F, false));
		Layer1.cubeList.add(new ModelBox(Layer1, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));

		Layer2 = new ModelRenderer(this);
		Layer2.setRotationPoint(0.0F, -14.0F, 0.0F);
		Layer1.addChild(Layer2);
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -19.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -25.0F, -23.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 8.0F, -21.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 13.0F, -12.0F, 9.3333F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -20.0F, -19.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -23.0F, -19.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, 2.0F, -24.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -8.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer2.cubeList.add(new ModelBox(Layer2, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));

		Layer0 = new ModelRenderer(this);
		Layer0.setRotationPoint(0.0F, 14.0F, 0.0F);
		Layer1.addChild(Layer0);
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -12.0F, -58.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 7.0F, -59.0F, -20.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -49.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -50.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -58.0F, 8.3333F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -20.0F, -32.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, 8.0F, -62.0F, -5.6667F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -51.0F, 2.3333F, 12, 12, 12, 0.0F, false));
		Layer0.cubeList.add(new ModelBox(Layer0, 0, 0, -6.0F, -6.0F, -5.6667F, 12, 12, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Layer1.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Layer1.rotateAngleY = f2;
	}
}