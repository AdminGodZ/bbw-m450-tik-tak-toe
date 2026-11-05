package ch.bbw.m450.tictactoe;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

/**
 * Example for an additional testing framework: ArchUnit checks the structure of the code.
 */
class ArchitectureTest {

	private final ClassFileImporter importer = new ClassFileImporter()
			.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS);

	@Test
	void allPlayersImplementTicTacToePlayer() {
		classes().that()
				.resideInAPackage("..players..")
				.should()
				.implement(TicTacToePlayer.class)
				.check(importer.importPackages("ch.bbw.m450.tictactoe"));
	}
}
