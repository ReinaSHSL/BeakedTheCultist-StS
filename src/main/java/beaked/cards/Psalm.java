package beaked.cards;

import beaked.Beaked;
import beaked.actions.WitherAction;
import beaked.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Psalm extends AbstractWitherCard {
    public static final String ID = "beaked:Psalm";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int DAMAGE = 4;
    private static final int WITHER_MINUS_DAMAGE = 2;
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    public Psalm() {
        super(ID, NAME, "beaked_img/cards/"+ Beaked.getActualID(ID)+".png", COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.BEAKED_YELLOW, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);

        this.baseMagicNumber = this.magicNumber = WITHER_MINUS_DAMAGE;
        this.baseDamage = this.damage = DAMAGE;
        this.baseMisc = this.misc = damage;
        this.isMultiDamage = true;
        this.witherEffect = "Decreases damage.";
        this.linkWitherAmountToMagicNumber = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new WitherAction(this));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,this.multiDamage,DamageInfo.DamageType.NORMAL,AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void applyPowers() {
        this.baseDamage = this.damage = this.misc;
        super.applyPowers();
    }

    public AbstractCard makeCopy() {
        return new Psalm();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.upgradeMisc(UPGRADE_PLUS_DAMAGE); // use upgradeMisc to update the witherable value (calls ApplyPowers)
        }
    }
}
