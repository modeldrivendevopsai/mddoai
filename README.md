# MDDOAI
This project transforms SoftWareArchitecture models into usable CI/CD pipelines using Model-driven techniques.
Find the [old documentation here](before_opensource.md)

## Building
### 1. Clone the repo
Run this command:

```
git clone https://github.com/modeldrivendevopsai/mddoai.git
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

`<Type>` - this is the type of transformation. Available transformation types are:
- `swarch2gitlab`: Transforms Software Architecture model into GitLab CI/CD pipeline (.gitlab-ci.yml)
- `pim2gitlab`: Transforms Platform Independent Model into GitLab CI/CD pipeline
- `psm2gitlab`: Transforms Gitlab Platform Specific Model into GitLab CI/CD pipeline

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

## How to Use Docker Images

You can pull and run MDDOAI Docker images from the GitHub Container Registry (GHCR).

### Pulling Images

```bash
# Pull the latest development version (recommended for development)
docker pull ghcr.io/modeldrivendevopsai/mddoai:1.0-snapshot

# Pull a specific production release
docker pull ghcr.io/modeldrivendevopsai/mddoai:1.0.1

# Pull the testing version (for feature branch testing)
docker pull ghcr.io/modeldrivendevopsai/mddoai:1.x-snapshot
```

### Running the Container

```bash
# Basic usage with mounted volumes
# $(pwd) = current directory, -v mounts host folders to container paths
docker run -v "$(pwd)/input:/app/input" -v "$(pwd)/output:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai:1.0-snapshot \
  swarch2gitlab "/app/input/model.swarch" "/app/output"

# Or using absolute paths
docker run -v "/path/to/input:/app/input" -v "/path/to/output:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai:1.0-snapshot \
  swarch2gitlab "/app/input/model.swarch" "/app/output"

# CI/CD pipeline usage (with pull and cleanup)
docker pull ghcr.io/modeldrivendevopsai/mddoai:1.0-snapshot
docker run --rm -v "$(pwd)/input:/app/input" -v "$(pwd)/output:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai:1.0-snapshot \
  swarch2gitlab "/app/input/model.swarch" "/app/output"
```

**Note:** The container expects the input model file and will generate the GitLab CI YAML in the output directory.

## Docker Image Versioning Strategy

MDDOAI uses a three-tier Docker image tagging strategy. This section explains each tag type, when to use it, and how the CI/CD pipeline manages versions.

---

## Versioning Tags

### `1.x-snapshot`

**Purpose:** Testing tag for all feature branches

**Characteristics:**
- Overwritten on every feature branch push
- Not safe for production use
- Useful for quick testing

**Example workflow:**
```bash
# Create feature branch
git checkout -b fix-parser-bug

# Make changes and push
git push origin fix-parser-bug
# GitHub Actions builds and pushes ghcr.io/.../mddoai:1.x-snapshot
```

---

### `1.0-snapshot`

**Purpose:** Latest code from the main branch

**Characteristics:**
- Overwritten on every main branch commit
- Semi-stable
- Recommended for development

**Example workflow:**
```bash
# Merge PR to main
git checkout main
git merge fix-parser-bug
git push origin main
# GitHub Actions builds and pushes ghcr.io/.../mddoai:1.0-snapshot
```

---

### `1.0.1`, `1.0.2`, `1.1.0`

**Purpose:** Permanent production releases

**Characteristics:**
- Never overwritten
- Never expires
- Follows Semantic Versioning (MAJOR.MINOR.PATCH)
- Ideal for production deployments

**Example workflow:**
```bash
# Ready to release? Create and push tag
git tag -a 1.0.1 -m "Release 1.0.1 - Fixed parser bug"
git push origin 1.0.1
# GitHub Actions builds and pushes ghcr.io/.../mddoai:1.0.1
```

---

## Comparison Table

| Tag           | Stability    | Overwritten?       | Use Case        |
|---------------|--------------|--------------------|-----------------|
| `1.x-snapshot`| Unstable     | Yes (any branch)   | Feature Testing |
| `1.0-snapshot`| Semi-stable  | Yes (main only)    | Development     |
| `1.0.1`       | Stable       | Never              | Production      |


See the image tags in the MDDOAI Container Registry for available tags available at https://github.com/modeldrivendevopsai/mddoai/pkgs/container/mddoai%2Fmddoai
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
- [E2E](https://modeldrivendevopsai.github.io/mddoai/e2eJacocoTestReport/html/)
- [Integration](https://modeldrivendevopsai.github.io/mddoai/integrationJacocoTestReport/html/)
- [Unit](https://modeldrivendevopsai.github.io/mddoai/unitJacocoTestReport/html/)

## Draw.io diagrams

View - Use Draw.io to open the file "drawio diagrams.xml"

Edit - Use the long-living branch "draw.io-diagrams" to edit the diagram, and then make a pull request once ready. The PR approver should always restore the merged branch. This URL should open draw.io directly - https://app.diagrams.net/#Hmodeldrivendevopsai%2Fmddoai%2Fdraw.io-diagrams%2Fdrawio%20diagrams.xml#%7B

## License

This project is licensed under the **Eclipse Public License 2.0 (EPL-2.0)**.  
You may obtain a copy of the license at:

[https://www.eclipse.org/legal/epl-2.0/](https://www.eclipse.org/legal/epl-2.0/)

Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
