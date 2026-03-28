package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterTypeCommand;
import seedu.address.model.property.PropertyTypeContainsKeywordsPredicate;

/**
 * Contains unit tests for FilterTypeCommandParser.
 */
public class FilterTypeCommandParserTest {

    private FilterTypeCommandParser parser = new FilterTypeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noPrefix_throwsParseException() {
        assertParseFailure(parser, "HDB", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterTypeCommand() {
        // no leading and trailing whitespaces
        FilterTypeCommand expectedFilterTypeCommand =
                new FilterTypeCommand(new PropertyTypeContainsKeywordsPredicate(Arrays.asList("HDB")));
        assertParseSuccess(parser, " t/HDB", expectedFilterTypeCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/ HDB \n \t Condo  \t",
                new FilterTypeCommand(new PropertyTypeContainsKeywordsPredicate(Arrays.asList("HDB", "Condo"))));
    }

    @Test
    public void parse_multipleKeywords_returnsFilterTypeCommand() {
        FilterTypeCommand expectedFilterTypeCommand =
                new FilterTypeCommand(new PropertyTypeContainsKeywordsPredicate(Arrays.asList("HDB", "Condo")));
        assertParseSuccess(parser, " t/HDB Condo", expectedFilterTypeCommand);
    }

    @Test
    public void parse_caseInsensitiveKeywords_returnsFilterTypeCommand() {
        FilterTypeCommand expectedFilterTypeCommand =
                new FilterTypeCommand(new PropertyTypeContainsKeywordsPredicate(Arrays.asList("hdb", "condo")));
        assertParseSuccess(parser, " t/hdb condo", expectedFilterTypeCommand);
    }
}

