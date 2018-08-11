package beaked.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FetchCardToHandAction extends AbstractGameAction {
    private AbstractCard card;
    private CardGroup group;

    public FetchCardToHandAction(AbstractCard card, CardGroup group) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.group = group;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (group == AbstractDungeon.player.hand){
                this.isDone = true;
                return;
            }

            if (group.contains(this.card) && AbstractDungeon.player.hand.size() < 10) {
                AbstractDungeon.player.hand.addToHand(this.card);
                this.card.unfadeOut();
                this.card.unhover();
                this.card.setAngle(0.0F, true);
                this.card.lighten(false);
                this.card.drawScale = 0.12F;
                this.card.targetDrawScale = 0.75F;
                this.card.fadingOut = false;
                group.removeCard(this.card);
                this.card.applyPowers();
            }

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
        }

        this.tickDuration();
        this.isDone = true;
    }
}
