// Chromium-based browsers drop a leading dot from a downloaded file's name
// (observed: `.gitlab-ci.yml` saves as `gitlab-ci.yml`), and the platform
// needs the dotted name exactly. The file is therefore always offered without
// the dot, so what gets saved is the same in every browser, and the screen
// says what to rename it to. A download is one file, never a folder, so an
// output file in a subfolder (".woodpecker/pipeline.yaml") is offered under
// its own name, and the screen says where it goes.
export function downloadName(outputFilename: string): string {
  const name = outputFilename.split('/').pop() ?? outputFilename;
  return name.replace(/^\.+/, '');
}

// Long enough for the browser to have started reading the blob; revoking in
// the same tick as the click can cancel the download in some browsers.
const REVOKE_DELAY_MS = 1000;

export function downloadText(filename: string, text: string) {
  const url = URL.createObjectURL(new Blob([text], { type: 'text/plain' }));
  const link = document.createElement('a');
  link.href = url;
  link.download = filename;
  link.click();
  setTimeout(() => URL.revokeObjectURL(url), REVOKE_DELAY_MS);
}
