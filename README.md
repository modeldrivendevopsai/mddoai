# MDDOAI

This project transforms Software Architecture models into usable CI/CD pipelines using model-driven techniques.

Find the [old documentation here](before_opensource.md)

---

## What Does MDDOAI Do?

MDDOAI automatically generates GitLab CI/CD pipeline configurations from software architecture models:

```
Input: Software Architecture Model (.swarch)
         ↓
MDDOAI Transformation
         ↓
Output: GitLab CI/CD Pipeline (.gitlab-ci.yml)
```

---

## Getting Started

There are **two ways** to use MDDOAI:

| Method | Best For | Setup Time |
|--------|----------|------------|
| **Docker** | Quick usage, CI/CD pipelines | 2 minutes |
| **Build from Source** | Development, customization | 5 minutes |

Choose your method below:

- **Want to try it quickly?** → [Use Docker](#using-docker-images)
- **Want to develop or customize?** → [Build from Source](#building-from-source)

---

## Using Docker Images

**Fastest way to get started** - no build required!

### Step 1: Pull the Image

```bash
# Production release (recommended)
docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1

# Or latest development version
docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0-snapshot
```

### Step 2: Prepare Your Input

Create directories and place your model file:

```bash
mkdir -p input output
# Place your .swarch file in input/ directory
```

### Step 3: Run the Transformation

```bash
docker run --rm \
  -v "$(pwd)/input:/app/input" \
  -v "$(pwd)/output:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1 \
  swarch2gitlab "/app/input/your-model.swarch" "/app/output"
```

**Result:** Your generated `.gitlab-ci.yml` will be in the `output/` directory!

### Docker Usage Notes

- **`$(pwd)`** = current directory
- **`-v`** mounts host folders to container paths
- **`--rm`** automatically removes the container after execution

**Alternative - Using absolute paths:**
```bash
docker run --rm \
  -v "/full/path/to/input:/app/input" \
  -v "/full/path/to/output:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1 \
  swarch2gitlab "/app/input/your-model.swarch" "/app/output"
```

**For CI/CD pipelines:**
```bash
docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1
docker run --rm \
  -v "$(pwd)/models:/app/input" \
  -v "$(pwd)/generated:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1 \
  swarch2gitlab "/app/input/architecture.swarch" "/app/output"
```

---

## Building from Source

**For development and customization**

### Prerequisites

- **Java 21** or later
- **Git** for cloning the repository

### Step 1: Clone the Repository

```bash
git clone https://github.com/modeldrivendevopsai/mddoai.git
cd mddoai
```

### Step 2: Build the Tool

Navigate to the `main` directory:

```bash
cd main
```

Run the Gradle build command:

```bash
./gradlew clean installDist
```

If you are doing repeated local builds without major changes, you can use:

```bash
./gradlew installDist
```

Use `clean` especially for the first run, after switching branches, or after changes to models/transformations that can leave stale build artifacts.

#### Rebuilding `libs/mddoai.jar` (after Eclipse model/template changes)

`libs/mddoai.jar` bundles compiled classes from the 4 Eclipse plugin projects and is required by the CLI. If you modify metamodels, Acceleo templates, or code generation files in Eclipse, you need to rebuild this JAR before running `installDist`.

**Prerequisites:** Eclipse must have compiled the plugin projects first (i.e. the `bin/` directories in each plugin project must be up to date).

```bash
cd main
./gradlew buildMddoaiJar
```

Then rebuild the CLI:

```bash
./gradlew installDist
```

**Note for Windows users:**
- In PowerShell: Use `./gradlew` (as shown above).
- In CMD: Use `.\gradlew` instead.
- On Linux/macOS: Use `./gradlew` (as shown above).


**Expected output:** `BUILD SUCCESSFUL`

This creates executable scripts in `build/install/com.mddoai/bin/`.

### Step 3: Run the Tool
```bash
./cli.bat <Type> <InputModelPath> <OutputFolder>
```

**Note for Windows users:** 
- In PowerShell: Use `./cli.bat` (as shown above).
- In CMD: Use `.\cli.bat` instead.
- On Linux/macOS: Use `./cli.sh` (if available) or adapt accordingly.
---

## Transformation Types

MDDOAI supports three transformation types:

| Command | Input Format | Output | Use Case |
|---------|-------------|--------|----------|
| `swarch2gitlab` | `.swarch` | `.gitlab-ci.yml` |  End-to-end transformation |
| `pim2gitlab` | `.pim` | `.gitlab-ci.yml` | Advanced - Custom PIM workflows |
| `psm2gitlab` | `.gitlabpsm` | `.gitlab-ci.yml` | Advanced - Direct PSM to YAML |

**Parameters:**

- `<Type>` - Transformation type (see table above)
- `<InputModelPath>` - Path to your input model file
- `<OutputFolder>` - Directory where generated files will be saved

---

## Usage Examples

**Note:** All commands use `./` for Unix-like systems and PowerShell. On Windows CMD, replace `./` with `.\` (e.g., `.\cli.bat` instead of `./cli.bat`).

### Example 1: Transform Architecture Model (Docker)

```bash
# Prepare
mkdir -p input output
cp my-app.swarch input/

# Run transformation
docker run --rm \
  -v "$(pwd)/input:/app/input" \
  -v "$(pwd)/output:/app/output" \
  ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1 \
  swarch2gitlab "/app/input/my-app.swarch" "/app/output"

# Check result
cat output/.gitlab-ci.yml
```

### Example 2: Transform Architecture Model (Built from Source)

```bash
./cli.bat swarch2gitlab ./input/my-app.swarch ./output
```


### Example 3: Using Included Test Models

Test with example models included in the repository:

```bash
./cli.bat swarch2gitlab ./src/test/resources/testCases/e2e/input1.swarch ./test/generatedCode
```
---
## Testing
Run tests to verify everything works correctly:

```bash
cd main

# Run all tests
./gradlew test integrationTest e2eTest

# Or run individually
./gradlew e2eTest           # End-to-end tests
./gradlew integrationTest   # Integration tests
./gradlew test              # Unit tests
```

**Note for Windows users:** 
- In PowerShell: Use `./gradlew` (as shown above).
- In CMD: Use `.\gradlew` instead.

### Coverage Reports

View test coverage:

- [E2E Coverage](https://modeldrivendevopsai.github.io/mddoai/e2eJacocoTestReport/html/)
- [Integration Coverage](https://modeldrivendevopsai.github.io/mddoai/integrationJacocoTestReport/html/)
- [Unit Coverage](https://modeldrivendevopsai.github.io/mddoai/unitJacocoTestReport/html/)

---

## Docker Image Versioning Strategy

MDDOAI uses a three-tier Docker image tagging strategy.

### Versioning Tags

#### `1.x-snapshot`

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

#### `1.0-snapshot`

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

#### `1.0.1`, `1.0.2`, `1.1.0`

**Purpose:** Permanent production releases

**Characteristics:**
- Never overwritten
- Never expires
- Follows Semantic Versioning (MAJOR.MINOR.PATCH)
- Ideal for production deployments

**Example workflow (automated — recommended):**

Go to Actions → Release → Run workflow. The workflow automatically computes the next version, generates AI release notes, tags the commit, and publishes the GitHub Release. The CI/CD pipeline then builds and pushes the Docker image tagged with the new version.

**Example workflow (manual):**
```bash
# Ready to release? Create and push tag
git tag 1.0.1
git push origin 1.0.1
# GitHub Actions builds and pushes ghcr.io/.../mddoai:1.0.1
```

### Comparison Table

| Tag | Stability | Overwritten? | Use Case |
|-----|-----------|--------------|----------|
| `1.x-snapshot` | Unstable | Yes (any branch) | Feature Testing |
| `1.0-snapshot` | Semi-stable | Yes (main only) | Development |
| `1.0.1` | Stable | Never | Production |

See the image tags in the [MDDOAI Container Registry](https://github.com/modeldrivendevopsai/mddoai/pkgs/container/mddoai%2Fmddoai).

---

## Dependencies

All project dependencies are managed through the build.gradle file using Gradle's dependency management infrastructure.

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
- [Overall](https://modeldrivendevopsai.github.io/mddoai/test/html/index.html)

## Release

Releases are automated via the [Release workflow](.github/workflows/release.yml):

1. Go to **Actions → Release → Run workflow**
2. Select bump type (`minor` by default)
3. Click **Run workflow**

The workflow auto-increments the version, generates AI release notes from merged PRs (using GitHub's release-notes API), pushes the semver tag, creates the GitHub Release, then calls the CI/CD pipeline directly to build, test, and publish the Docker image. No extra secrets or setup required.

## AI Agents

This project uses GitHub Copilot agents for PR review, code quality checks, test case generation, and transformation debugging. See [docs/agents.md](docs/agents.md) for usage, invocation commands, and design rationale.

---

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
