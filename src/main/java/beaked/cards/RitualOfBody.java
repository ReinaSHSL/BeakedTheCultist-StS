package beaked.cards;

import basemod.abstracts.CustomCard;
import beaked.Beaked;
import beaked.patches.AbstractCardEnum;
import beaked.powers.BodyRitualPower;
import beaked.powers.MurmursPlusPower;
import beaked.powers.MurmursPower;
import beaked.powers.RitualPlayerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RitualOfBody extends CustomCard {
    public static final String ID = "beaked:RitualOfBody";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final int COST = 2;
    public static final int RITUAL = -1;
    public static final int ARMOR_PER_TURN = 1;
    public static final int UPGRADE_PLUS_ARMOR_PER_TURN = 1;

    public RitualOfBody() {
        super(ID, NAME, "beaked_img/cards/"+ Beaked.getActualID(ID)+".png", COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.BEAKED_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = ARMOR_PER_TURN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new RitualPlayerPower(p,RITUAL),RITUAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BodyRitualPower(p, this.magicNumber),this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RitualOfBody();
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_ARMOR_PER_TURN);
        }
    }
}
