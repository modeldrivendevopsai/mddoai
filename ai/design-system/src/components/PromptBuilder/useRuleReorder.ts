import { useState } from "react"
import type { DragEvent } from "react"

/**
 * Drag-and-drop reordering of a list of rules: which row is held, where it would land
 * (drawn as a line above or below a row), and the reordered list handed to `onReorder` on
 * drop. A drop onto the held row's own place changes nothing.
 */
export function useRuleReorder(items: string[], onReorder: (items: string[]) => void) {
  const [draggedIndex, setDraggedIndex] = useState<number | null>(null)
  const [dropIndex, setDropIndex] = useState<number | null>(null)

  const clear = () => {
    setDraggedIndex(null)
    setDropIndex(null)
  }

  const over = (event: DragEvent<HTMLLIElement>, index: number) => {
    event.preventDefault()
    const rect = event.currentTarget.getBoundingClientRect()
    setDropIndex(event.clientY < rect.top + rect.height / 2 ? index : index + 1)
  }

  const drop = (event: DragEvent<HTMLLIElement>) => {
    event.preventDefault()
    if (draggedIndex === null || dropIndex === null || dropIndex === draggedIndex) {
      clear()
      return
    }
    const copy = [...items]
    const [moved] = copy.splice(draggedIndex, 1)
    copy.splice(dropIndex > draggedIndex ? dropIndex - 1 : dropIndex, 0, moved)
    onReorder(copy)
    clear()
  }

  return { dropIndex, start: setDraggedIndex, end: clear, over, drop }
}
