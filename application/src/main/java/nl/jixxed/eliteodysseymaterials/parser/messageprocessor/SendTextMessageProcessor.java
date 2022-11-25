package nl.jixxed.eliteodysseymaterials.parser.messageprocessor;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import nl.jixxed.eliteodysseymaterials.constants.OdysseyBlueprintConstants;
import nl.jixxed.eliteodysseymaterials.domain.ApplicationState;
import nl.jixxed.eliteodysseymaterials.enums.NotificationType;
import nl.jixxed.eliteodysseymaterials.enums.OdysseyMaterial;
import nl.jixxed.eliteodysseymaterials.service.LocaleService;
import nl.jixxed.eliteodysseymaterials.service.NotificationService;
import nl.jixxed.eliteodysseymaterials.service.StorageService;
import nl.jixxed.eliteodysseymaterials.service.WishlistService;
import nl.jixxed.eliteodysseymaterials.service.event.EventService;
import nl.jixxed.eliteodysseymaterials.service.event.PointToOdysseyResourceEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class SendTextMessageProcessor implements MessageProcessor {

    @Override
    public void process(final JsonNode journalMessage) {
        final String message = asTextOrBlank(journalMessage, "Message");
        ApplicationState.getInstance().getPreferredCommander().ifPresent(commander -> {
            if (message.startsWith("CMDR " + commander.getName())) {
                final Pattern pattern = Pattern.compile("^.*\\((.*)\\)$");
                final Matcher matcher = pattern.matcher(message);
                if (matcher.find()) {
                    try {
                        final OdysseyMaterial odysseyMaterial = OdysseyMaterial.forLocalizedName(matcher.group(1));
                        EventService.publish(new PointToOdysseyResourceEvent(odysseyMaterial));
                        final String text;
                        final Integer backPackValue = StorageService.getMaterialStorage(odysseyMaterial).getBackPackValue();
                        final String backPackText = backPackValue > 0 ? "(" + backPackValue + ")" : "";
                        if (WishlistService.isMaterialOnWishlist(odysseyMaterial)) {
                            text = LocaleService.getLocalizedStringForCurrentLocale(odysseyMaterial.getLocalizationKey()) + " - Wishlist - " + StorageService.getMaterialStorage(odysseyMaterial).getTotalValue() + backPackText + "/" + WishlistService.getAllWishlistsCount(odysseyMaterial);
                            NotificationService.showInformation(NotificationType.WISHLIST_POINT, "Material information", text);
                        } else if (OdysseyBlueprintConstants.isEngineeringOrBlueprintIngredientWithOverride(odysseyMaterial)) {
                            text = LocaleService.getLocalizedStringForCurrentLocale(odysseyMaterial.getLocalizationKey()) + " - Engineer/Blueprint - " + StorageService.getMaterialStorage(odysseyMaterial).getTotalValue() + backPackText;
                            NotificationService.showInformation(NotificationType.RELEVANT_POINT, "Material information", text);
                        } else {
                            text = LocaleService.getLocalizedStringForCurrentLocale(odysseyMaterial.getLocalizationKey()) + " - Irrelevant";
                            NotificationService.showInformation(NotificationType.IRRELEVANT_POINT, "Material information", text);
                        }
                    } catch (final IllegalArgumentException ex) {
                        log.warn("Resource not an OdysseyMaterial");
                    }
                }
            }
        });
    }


}
