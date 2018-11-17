package beaked.relics;

import basemod.abstracts.CustomRelic;
import beaked.actions.ForcedEndTurnAction;
import beaked.powers.RitualPlayerPower;
import beaked.tools.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;

import java.util.Iterator;

public class RitualPlumage extends CustomRelic implements ClickableRelic {
    public static final String ID = "beaked:RitualPlumage";
    private static boolean usedThisCombat = false;
    private AbstractPlayer p = AbstractDungeon.player;
    private GameActionManager am = AbstractDungeon.actionManager;
    private static int ritualAmount = 2;

    public RitualPlumage() {
        super(ID, TextureLoader.getTexture("beaked_images/relics/RitualPlumage.png"),
                TextureLoader.getTexture("beaked_images/relics/outline/RitualPlumage.png"),
                RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + ritualAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new RitualPlumage();
    }

    @Override
    public void onRightClick() {
        System.out.println("??? " + AbstractDungeon.getCurrRoom().phase + " " + usedThisCombat);
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !usedThisCombat) {
            am.addToBottom(new ApplyPowerAction(p, p, new RitualPlayerPower(p, ritualAmount), ritualAmount));
            am.addToBottom(new ForcedEndTurnAction());
            usedThisCombat = true;
        }
    }

    @Override
    public void atBattleStart() {
        this.usedThisCombat = false;
    }
}