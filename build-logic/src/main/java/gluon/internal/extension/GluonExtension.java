package gluon.internal.extension;

import gluon.internal.Versions;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class GluonExtension {
	private static final String[] TASKS_TO_DISABLE = {
		"genSourcesWithFernFlower",
		"genSourcesWithVineflower",
		"genSourcesWithCfr",
		"genSources"
	};
	protected final Project project;

	public GluonExtension(Project project) {
		this.project = project;

		this.project.afterEvaluate(p -> {
			for (var task : TASKS_TO_DISABLE) {
				Task disabled = p.getTasks().findByName(task);
				if (disabled != null) {
					disabled.setEnabled(false);
				}
			}
		});
	}

	public void setVersion(String version) {
		this.project.setVersion(version + '+' + Versions.MINECRAFT_VERSION.version()
				+ (System.getenv("SNAPSHOTS_URL") != null ? "-SNAPSHOT" : ""));
	}
}
