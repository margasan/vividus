/*
 * Copyright 2019-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vividus.steps.ui.web;

import static org.vividus.ui.web.action.search.WebLocatorType.LINK_URL;

import javax.inject.Inject;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vividus.steps.ui.validation.IBaseValidations;
import org.vividus.ui.action.search.Locator;
import org.vividus.ui.monitor.TakeScreenshotOnFailure;
import org.vividus.ui.web.action.search.WebLocatorType;

@TakeScreenshotOnFailure
public class LinkSteps
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkSteps.class);

    private static final String TEXT = "text";

    @Inject private IBaseValidations baseValidations;

    /**
     * @deprecated Use step: "Then number of elements found by `$locator` is $comparisonRule `$quantity`"
     *
     * Checks that searchContext contains <b>linkItems</b> with expected text and url
     * (<b>text</b> and <b>href attribute</b> values):
     * <table border="1" style="width:10%">
     * <caption>A table of attributes</caption>
     * <thead>
     * <tr>
     * <td>
     * <b>text</b></td>
     * <td>
     * <b>link</b></td>
     * </tr>
     * </thead> <tbody>
     * <tr>
     * <td>linkItemText1</td>
     * <td>linkItemLink1</td>
     * </tr>
     * <tr>
     * <td>linkItemText2</td>
     * <td>linkItemLink2</td>
     * </tr>
     * <tr>
     * <td>linkItemText3</td>
     * <td>linkItemLink3</td>
     * </tr>
     * </tbody>
     * </table>
     * @param expectedLinkItems A table of expected <b>link</b> items
     */
    @Deprecated(since = "0.5.0", forRemoval = true)
    @Then(value = "context contains list of link items with the text and link:$expectedLinkItems", priority = 1)
    public void ifLinkItemsWithTextAndLink(ExamplesTable expectedLinkItems)
    {
        LOGGER.warn("The step: \"Then context contains list of link items with the text and link:$expectedLinkItems\""
                + " is deprecated and will be removed in VIVIDUS 0.6.0. Use step: "
                + "\"Then number of elements found by `$locator` is $comparisonRule `$quantity`\"");
        for (Parameters row : expectedLinkItems.getRowsAsParameters(true))
        {
            String text = row.valueAs(TEXT, String.class);
            String url = row.valueAs("link", String.class);
            Locator attributes = new Locator(WebLocatorType.LINK_TEXT, text).addFilter(
                    LINK_URL, url);
            baseValidations.assertIfElementExists("Link with attributes: " + attributes, attributes);
        }
    }

    /**
     * @deprecated Use step: "Then number of elements found by `$locator` is $comparisonRule `$quantity`"
     *
     * Checks that searchContext contains <b>linkItems</b> with expected text
     * <p>
     * A <b>menu</b> is defined by a {@literal <nav>} tag, which contains a list of <b>menu items</b>. The first level
     * of this list will be a <b>first-level menu</b>.
     * <p>
     * Actions performed at this step:
     * <ul>
     * <li>Iterate through <b>expected links</b> list
     * </ul>
     * Example:
     * <table border="1" style="width:10%">
     * <caption>A table of links</caption>
     * <thead><tr><td><b>text</b></td></tr></thead>
     * <tbody>
     * <tr><td>linkItem1</td></tr>
     * <tr><td>linkItem2</td></tr>
     * <tr><td>linkItem3</td></tr>
     * </tbody>
     * </table>
     * @param expectedLinkItems A table of expected <b>link</b> items (<b>text</b> values):
     */
    @Deprecated(since = "0.5.0", forRemoval = true)
    @Then("context contains list of link items with the text:$expectedLinkItems")
    public void ifLinkItemsWithTextExists(ExamplesTable expectedLinkItems)
    {
        LOGGER.warn("The step: \"Then context contains list of link items with the text:$expectedLinkItems\""
                + " is deprecated and will be removed in VIVIDUS 0.6.0."
                + " Use step: \"Then number of elements found by `$locator` is $comparisonRule `$quantity`\"");
        expectedLinkItems.getRowsAsParameters(true).stream()
                .<String>map(row -> row.valueAs(TEXT, String.class))
                .forEach(text -> {
                    Locator attributes = new Locator(WebLocatorType.LINK_TEXT, text);
                    baseValidations.assertIfElementExists(String.format("Link with text '%s'", text), attributes);
                });
    }
}
