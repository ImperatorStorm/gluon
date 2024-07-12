package gluon.internal.extension;

import javax.inject.Inject;

import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

public class GluonLibraryExtension extends GluonExtension {
	private final Property<String> libraryName;

	@Inject
	public GluonLibraryExtension(ObjectFactory factory, Project project) {
		super(project);
		this.libraryName = factory.property(String.class);
		this.libraryName.finalizeValueOnRead();
	}

	public Property<String> getLibraryName() {
		return this.libraryName;
	}

	public void setLibraryName(String name) {
		this.libraryName.set(name);
	}
}
