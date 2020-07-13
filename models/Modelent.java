// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelent extends EntityModel {
	private final ModelRenderer Head;
	private final ModelRenderer Head0;
	private final ModelRenderer Head1;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer Rarm;
	private final ModelRenderer Larm;
	private final ModelRenderer Rleg;
	private final ModelRenderer Lleg;
	private final ModelRenderer Body;

	public Modelent() {
		textureWidth = 128;
		textureHeight = 128;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(-0.0333F, 1.6667F, -0.0333F);
		Head.cubeList.add(new ModelBox(Head, 116, 0, -1.5667F, -5.6667F, -1.5667F, 3, 6, 3, 0.0F, false));

		Head0 = new ModelRenderer(this);
		Head0.setRotationPoint(-0.0667F, -6.6667F, -1.4667F);
		Head.addChild(Head0);
		setRotationAngle(Head0, 0.6981F, 0.0F, 0.0F);
		Head0.cubeList.add(new ModelBox(Head0, 116, 0, -1.5F, -3.0F, -1.5F, 3, 6, 3, 0.0F, false));

		Head1 = new ModelRenderer(this);
		Head1.setRotationPoint(0.0333F, 22.3333F, 0.0333F);
		Head.addChild(Head1);
		Head1.cubeList.add(new ModelBox(Head1, 0, 0, 3.0F, -37.0F, -12.0F, 3, 3, 3, 0.0F, false));
		Head1.cubeList.add(new ModelBox(Head1, 0, 0, -5.0F, -38.0F, -12.0F, 3, 3, 3, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(2.5F, -32.5F, -6.5F);
		Head1.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -1.1345F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 68, -0.5F, -0.5F, -4.5F, 1, 1, 9, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-2.5F, -32.5F, -6.5F);
		Head1.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.7854F, 1.1345F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 68, -0.5F, -0.5F, -4.5F, 1, 1, 9, 0.0F, false));

		Rarm = new ModelRenderer(this);
		Rarm.setRotationPoint(-6.0F, 4.5F, 0.0F);
		Rarm.cubeList.add(new ModelBox(Rarm, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));

		Larm = new ModelRenderer(this);
		Larm.setRotationPoint(6.0F, 4.5F, 0.0F);
		Larm.cubeList.add(new ModelBox(Larm, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));

		Rleg = new ModelRenderer(this);
		Rleg.setRotationPoint(-2.0F, 15.5F, 0.0F);
		Rleg.cubeList.add(new ModelBox(Rleg, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));

		Lleg = new ModelRenderer(this);
		Lleg.setRotationPoint(2.0F, 15.5F, 0.0F);
		Lleg.cubeList.add(new ModelBox(Lleg, 112, 113, -2.0F, -2.5F, -2.0F, 4, 11, 4, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 7.5F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 113, -4.0F, -5.5F, -2.0F, 8, 11, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Head.render(f5);
		Rarm.render(f5);
		Larm.render(f5);
		Rleg.render(f5);
		Lleg.render(f5);
		Body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.Larm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.Lleg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.Rleg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Rarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
	}
}