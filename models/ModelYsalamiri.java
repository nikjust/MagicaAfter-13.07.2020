// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelYsalamiri extends EntityModel {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer LegL;
	private final ModelRenderer LegR;
	private final ModelRenderer ArmL;
	private final ModelRenderer ArmR;
	private final ModelRenderer Tail;
	private final ModelRenderer Tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer Tail3;

	public ModelYsalamiri() {
		textureWidth = 32;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 20, 18, -1.0F, -3.0F, -6.0F, 2, 2, 3, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 1, 24, -1.0F, -3.0F, 2.0F, 2, 2, 4, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 13, 23, -2.0F, -3.0F, -3.0F, 4, 2, 5, 0.0F, false));

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(0.0F, 24.0F, 0.0F);
		LegL.cubeList.add(new ModelBox(LegL, 27, 24, 0.5F, -2.0F, 3.0F, 1, 2, 1, 0.0F, false));

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(0.0F, 24.0F, 0.0F);
		LegR.cubeList.add(new ModelBox(LegR, 10, 24, -1.5F, -2.0F, 3.0F, 1, 2, 1, 0.0F, false));

		ArmL = new ModelRenderer(this);
		ArmL.setRotationPoint(0.0F, 24.0F, 0.0F);
		ArmL.cubeList.add(new ModelBox(ArmL, 16, 24, 1.5F, -2.0F, -3.0F, 1, 2, 1, 0.0F, false));

		ArmR = new ModelRenderer(this);
		ArmR.setRotationPoint(0.0F, 24.0F, 0.0F);
		ArmR.cubeList.add(new ModelBox(ArmR, 2, 24, -2.5F, -2.0F, -3.0F, 1, 2, 1, 0.0F, false));

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, 23.25F, 6.5F);
		Tail.cubeList.add(new ModelBox(Tail, 4, 4, -0.5F, -1.25F, -0.5F, 1, 1, 2, 0.0F, false));

		Tail1 = new ModelRenderer(this);
		Tail1.setRotationPoint(0.0F, 0.0F, 2.0F);
		Tail.addChild(Tail1);
		Tail1.cubeList.add(new ModelBox(Tail1, 10, 4, -0.5F, -1.25F, -0.5F, 1, 1, 2, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 2.0F);
		Tail1.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 16, 4, -0.5F, -1.25F, -0.5F, 1, 1, 2, 0.0F, false));

		Tail3 = new ModelRenderer(this);
		Tail3.setRotationPoint(0.0F, -0.75F, 2.0F);
		tail2.addChild(Tail3);
		Tail3.cubeList.add(new ModelBox(Tail3, 22, 4, -0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		LegL.render(f5);
		LegR.render(f5);
		ArmL.render(f5);
		ArmR.render(f5);
		Tail.render(f5);
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
		this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.tail2.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.Tail.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.ArmR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Tail1.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.Tail3.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.ArmL.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}