// A model file the screen is working with, however it got there.
export interface ModelSource {
  filename: string;
  content: string;
  // Where in the workspace it was loaded from; null for an upload.
  path: string | null;
}
