// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelfff extends EntityModel {
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;

	public Modelfff() {
		textureWidth = 64;
		textureHeight = 64;

		leftLeg2 = new ModelRenderer(this);
		leftLeg2.setRotationPoint(2.0F, 12.0F, 0.0F);
		leftLeg2.cubeList.add(new ModelBox(leftLeg2, 24, 24, -2.0F, 6.0F, -3.0F, 5, 1, 6, 0.0F, false));
		leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 7.0F, 2.0F, 2, 6, 1, 0.0F, false));
		leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 13.0F, 1.0F, 2, 1, 1, 0.0F, false));
		leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 14.0F, 0.0F, 2, 1, 1, 0.0F, false));
		leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 14.0F, -4.0F, 2, 1, 1, 0.0F, false));
		leftLeg2.cubeList.add(new ModelBox(leftLeg2, 0, 0, -1.0F, 15.0F, -3.0F, 2, 1, 3, 0.0F, false));

		rightLeg2 = new ModelRenderer(this);
		rightLeg2.setRotationPoint(-2.0F, 12.0F, 0.0F);
		rightLeg2.cubeList.add(new ModelBox(rightLeg2, 24, 24, -3.0F, 6.0F, -3.0F, 5, 1, 6, 0.0F, false));
		rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 7.0F, 2.0F, 2, 6, 1, 0.0F, false));
		rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 13.0F, 1.0F, 2, 1, 1, 0.0F, false));
		rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 14.0F, 0.0F, 2, 1, 1, 0.0F, false));
		rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 14.0F, -4.0F, 2, 1, 1, 0.0F, false));
		rightLeg2.cubeList.add(new ModelBox(rightLeg2, 0, 0, -1.0F, 15.0F, -3.0F, 2, 1, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		leftLeg2.render(f5);
		rightLeg2.render(f5);
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