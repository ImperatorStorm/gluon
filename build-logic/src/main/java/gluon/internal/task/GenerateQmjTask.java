package gluon.internal.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.inject.Inject;

import gluon.internal.Versions;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Nested;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import gluon.internal.extension.GluonModuleExtensionImpl;
import gluon.internal.json.QmjBuilder;

public abstract class GenerateQmjTask extends DefaultTask {
	@OutputDirectory
	public abstract DirectoryProperty getOutputDir();

	@Nested
	public abstract Property<GluonModuleExtensionImpl> getGluonModule();

	@Inject
	public GenerateQmjTask() {
		this.setGroup("generation");
	}

	@TaskAction
	public void generateQmj() throws IOException {
		Path output = this.getOutputDir().getAsFile().get().toPath().resolve("quilt.mod.json");
		if (Files.exists(output)) {
			Files.delete(output);
		}

		QmjBuilder.buildQmj(getProject(), getProject().getVersion().toString(), Versions.LOADER_VERSION, Versions.MINECRAFT_VERSION, this.getGluonModule().get(), output);
	}
}
