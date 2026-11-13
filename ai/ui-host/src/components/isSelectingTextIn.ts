/**
 * Whether a press inside `row` ended a text selection there, such as copying a
 * file name. A table row that opens something when pressed must ignore that
 * press, or selecting its text would open it.
 */
export function isSelectingTextIn(row: HTMLElement): boolean {
  const selection = window.getSelection();
  return selection !== null && !selection.isCollapsed && row.contains(selection.anchorNode);
}
