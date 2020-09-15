
@OnlyIn(Dist.CLIENT)
public static class ModelEvocatorBossModel extends EntityModel<T>extends T implements IHasArm,IHasHead
{
	protected final ModelRenderer head;
	private final ModelRenderer hat;
	protected final ModelRenderer body;
	protected final ModelRenderer arms;
	protected final ModelRenderer field_217143_g;
	protected final ModelRenderer field_217144_h;
	private final ModelRenderer nose;
	protected final ModelRenderer rightArm;
	protected final ModelRenderer leftArm;
	private float field_217145_m;

	public ModelEvocatorBossModel(final float p_i47227_1_, final float p_i47227_2_, final int p_i47227_3_,
			final int p_i47227_4_) {
		(this.head = new ModelRenderer((Model) this).setTextureSize(p_i47227_3_, p_i47227_4_)).setRotationPoint(0.0f,
				0.0f + p_i47227_2_, 0.0f);
		this.head.setTextureOffset(0, 0).addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8, p_i47227_1_);
		(this.hat = new ModelRenderer((Model) this, 32, 0).setTextureSize(p_i47227_3_, p_i47227_4_)).addBox(-4.0f,
				-10.0f, -4.0f, 8, 12, 8, p_i47227_1_ + 0.45f);
		this.head.addChild(this.hat);
		this.hat.showModel = false;
		(this.nose = new ModelRenderer((Model) this).setTextureSize(p_i47227_3_, p_i47227_4_)).setRotationPoint(0.0f,
				p_i47227_2_ - 2.0f, 0.0f);
		this.nose.setTextureOffset(24, 0).addBox(-1.0f, -1.0f, -6.0f, 2, 4, 2, p_i47227_1_);
		this.head.addChild(this.nose);
		(this.body = new ModelRenderer((Model) this).setTextureSize(p_i47227_3_, p_i47227_4_)).setRotationPoint(0.0f,
				0.0f + p_i47227_2_, 0.0f);
		this.body.setTextureOffset(16, 20).addBox(-4.0f, 0.0f, -3.0f, 8, 12, 6, p_i47227_1_);
		this.body.setTextureOffset(0, 38).addBox(-4.0f, 0.0f, -3.0f, 8, 18, 6, p_i47227_1_ + 0.5f);
		(this.arms = new ModelRenderer((Model) this).setTextureSize(p_i47227_3_, p_i47227_4_)).setRotationPoint(0.0f,
				0.0f + p_i47227_2_ + 2.0f, 0.0f);
		this.arms.setTextureOffset(44, 22).addBox(-8.0f, -2.0f, -2.0f, 4, 8, 4, p_i47227_1_);
		final ModelRenderer lvt_5_1_ = new ModelRenderer((Model) this, 44, 22).setTextureSize(p_i47227_3_, p_i47227_4_);
		lvt_5_1_.mirror = true;
		lvt_5_1_.addBox(4.0f, -2.0f, -2.0f, 4, 8, 4, p_i47227_1_);
		this.arms.addChild(lvt_5_1_);
		this.arms.setTextureOffset(40, 38).addBox(-4.0f, 2.0f, -2.0f, 8, 4, 4, p_i47227_1_);
		(this.field_217143_g = new ModelRenderer((Model) this, 0, 22).setTextureSize(p_i47227_3_, p_i47227_4_))
				.setRotationPoint(-2.0f, 12.0f + p_i47227_2_, 0.0f);
		this.field_217143_g.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, p_i47227_1_);
		this.field_217144_h = new ModelRenderer((Model) this, 0, 22).setTextureSize(p_i47227_3_, p_i47227_4_);
		this.field_217144_h.mirror = true;
		this.field_217144_h.setRotationPoint(2.0f, 12.0f + p_i47227_2_, 0.0f);
		this.field_217144_h.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, p_i47227_1_);
		(this.rightArm = new ModelRenderer((Model) this, 40, 46).setTextureSize(p_i47227_3_, p_i47227_4_)).addBox(-3.0f,
				-2.0f, -2.0f, 4, 12, 4, p_i47227_1_);
		this.rightArm.setRotationPoint(-5.0f, 2.0f + p_i47227_2_, 0.0f);
		this.leftArm = new ModelRenderer((Model) this, 40, 46).setTextureSize(p_i47227_3_, p_i47227_4_);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, p_i47227_1_);
		this.leftArm.setRotationPoint(5.0f, 2.0f + p_i47227_2_, 0.0f);
	}

	public void render(final T p_78088_1_, final float p_78088_2_, final float p_78088_3_, final float p_78088_4_,
			final float p_78088_5_, final float p_78088_6_, final float p_78088_7_) {
		this.setRotationAngles(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
		this.head.render(p_78088_7_);
		this.body.render(p_78088_7_);
		this.field_217143_g.render(p_78088_7_);
		this.field_217144_h.render(p_78088_7_);
		if (p_78088_1_.getArmPose() == AbstractIllagerEntity.ArmPose.CROSSED) {
			this.arms.render(p_78088_7_);
		} else {
			this.rightArm.render(p_78088_7_);
			this.leftArm.render(p_78088_7_);
		}
	}

	public void setLivingAnimations(final T p_212843_1_, final float p_212843_2_, final float p_212843_3_,
			final float p_212843_4_) {
		this.field_217145_m = (float) p_212843_1_.getItemInUseMaxCount();
		super.setLivingAnimations((Entity) p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
	}

	private ModelRenderer getArm(final HandSide p_191216_1_) {
		if (p_191216_1_ == HandSide.LEFT) {
			return this.leftArm;
		}
		return this.rightArm;
	}

	public ModelRenderer func_205062_a() {
		return this.hat;
	}

	public ModelRenderer func_205072_a() {
		return this.head;
	}

	public void postRenderArm(final float p_187073_1_, final HandSide p_187073_2_) {
		this.getArm(p_187073_2_).postRender(0.0625f);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}
