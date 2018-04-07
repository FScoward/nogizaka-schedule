package service

import models.Show

trait OutputService {
  def show(target: Show): Unit

  def showAll(targetList: Seq[Show]): Unit
}
