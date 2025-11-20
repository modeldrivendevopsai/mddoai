package main.java.mddoai.generators.wrappers;

import com.mddoai.codegeneration.gitlab.acceleo.main.Generate;

public class GitLabGenerator implements ICodeGeneratorWrapper {

	@Override
	public void execute(String[] args) throws Exception {
		Generate.main(args);
	}

}
