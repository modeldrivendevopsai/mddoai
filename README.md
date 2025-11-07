# MDDOAI
This project transforms software architecture models into usable GitLab CI pipeline YAML code.

## Building
### 1. Clone the repo
Run this command:

```
git clone https://gitlab.com/model-driven-devops-ai/core.git
```

to clone the repo.

### 2. Build the tool
To build the tool first change directory to the `main` directory

```
cd ./main
```

Then run this **Gradle** command:

```
gradlew installDist
```

## Running
To use it run this command from the main directory:

```
./cli.bat <Type> <InputModelPath> <OutputFolder>
```

`<Type>` - this is the type of transformation. Currently only one is possible swarch2gitlab. This will take in the SW Arch model and generate a .gitlab-ci.yml file.

`<InputModelPath>` - This is the path to the input model.

`<OutputFolder>` - This is the output folder where the generated code will output to.

For example the command can look something like this:

```
./cli.bat swarch2gitlab ./src/test/resources/testCases/e2e/input1.swarch ./test/generatedCode
```

## Testing
To run the E2E, integration and unit tests you can run these commands individually:

```
./gradlew e2eTest
./gradlew integrationTest
./gradlew test
```

## Dependency Management
All project dependencies are managed through the build.gradle file using Gradle’s dependency management infrastructure.

### Core Libraries

- Commons IO – File handling utilities (copying, filtering, I/O abstraction)

- Commons CLI – Command-line argument parsing

### Eclipse Platform & Modeling

- Eclipse Core Runtime – OSGi-based plugin framework for model loading and service resolution

 - EMF (Eclipse Modeling Framework) – Model definition, XMI serialization, code generation

- OCL (Object Constraint Language) – Constraint handling for model validation

- Acceleo – Template-based model-to-text code generation (supports OCL)

### Transformation Engines

- ATL / Acceleo (via local JARs in libs/) – Model-to-model and model-to-text transformations

- Epsilon – Advanced model management: merging, migration, and transformation (EML, EMF, plain XML)

### Parsing Runtime

- LPG Runtime – Lightweight parser generator runtime used by ATL and OCL parsers

### Testing

- JUnit 4 & 5 – Unit, integration, and end-to-end test framework

- JUnit Platform – Underlying test engine for running mixed JUnit versions

- JUnit Vintage – Enables running legacy JUnit 4 tests on the JUnit 5 platform

To add or update dependencies, modify the dependencies block in the Gradle build script located in `main/build.gradle`.

## Project Structure
The project folder structure consists of these folders:

- `code_generation` - this folder contains the necessary Acceleo files to generate code from models.

- `designs` - this folder contains all Eclipse Sirius viewpoint projects that are used to visualize models and edit them.

- `feature` - this folder contains all  the necessary packages in one feature project

- `install_necessary_packages` - contains all the .zip files that need to be installed via the install new software view before using the tools in the project.

- `main` - this folder contains the MDDOAI itself. Under this folder there also is:

	- `transformations` - a folder responsible to translate from one model to another.

- `meta_models` - this folder contains meta-models used by this project that are modeled using the EMF (Eclipse Modelling Framework).

- `pipeline_tests` - this contains bash scripts that the pipeline runs to do some testing, for example to test wheter a file exists or not.

- `update_site` - this folder contains the reference to the feature from the feature folder and it is responsible for building the update site.

## Coverage reports
[E2E](https://modeldrivendevopsai.github.io/mddoai/e2eJacocoTestReport/html/)
[Integration](https://modeldrivendevopsai.github.io/mddoai/integrationJacocoTestReport/html/)
[Unit](https://modeldrivendevopsai.github.io/mddoai/unitJacocoTestReport/html/)

## License

This project is licensed under the **Eclipse Public License 2.0 (EPL-2.0)**.  
You may obtain a copy of the license at:

[https://www.eclipse.org/legal/epl-2.0/](https://www.eclipse.org/legal/epl-2.0/)

Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
