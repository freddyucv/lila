package lila.pref

import play.api.data._
import play.api.data.Forms._

import lila.user.User

private[pref] final class DataForm {

  val pref = Form(mapping(
    "autoQueen" -> number.verifying(Pref.AutoQueen.choices.toMap contains _),
    "autoThreefold" -> number.verifying(Pref.AutoThreefold.choices.toMap contains _),
    "takeback" -> number.verifying(Pref.Takeback.choices.toMap contains _),
    "clockTenths" -> number.verifying(Set(0, 1) contains _),
    "clockBar" -> number.verifying(Set(0, 1) contains _),
    "clockSound" -> number.verifying(Set(0, 1) contains _),
    "follow" -> number.verifying(Set(0, 1) contains _),
    "highlight" -> number.verifying(Set(0, 1) contains _),
    "destination" -> number.verifying(Set(0, 1) contains _),
    "coords" -> number.verifying(Pref.Coords.choices.toMap contains _),
    "replay" -> number.verifying(Pref.Replay.choices.toMap contains _),
    "blindfold" -> number.verifying(Pref.Blindfold.choices.toMap contains _),
    "challenge" -> number.verifying(Pref.Challenge.choices.toMap contains _),
    "premove" -> number.verifying(Set(0, 1) contains _),
    "animation" -> number.verifying(Set(0, 1, 2, 3) contains _),
    "submitMove" -> number.verifying(Set(0, 1) contains _),
    "captured" -> number.verifying(Set(0, 1) contains _)
  )(PrefData.apply)(PrefData.unapply))

  case class PrefData(
      autoQueen: Int,
      autoThreefold: Int,
      takeback: Int,
      clockTenths: Int,
      clockBar: Int,
      clockSound: Int,
      follow: Int,
      highlight: Int,
      destination: Int,
      coords: Int,
      replay: Int,
      blindfold: Int,
      challenge: Int,
      premove: Int,
      animation: Int,
      submitMove: Int,
      captured: Int) {

    def apply(pref: Pref) = pref.copy(
      autoQueen = autoQueen,
      autoThreefold = autoThreefold,
      takeback = takeback,
      clockTenths = clockTenths == 1,
      clockBar = clockBar == 1,
      clockSound = clockSound == 1,
      follow = follow == 1,
      highlight = highlight == 1,
      destination = destination == 1,
      coords = coords,
      replay = replay,
      blindfold = blindfold,
      challenge = challenge,
      premove = premove == 1,
      animation = animation,
      submitMove = submitMove,
      captured = captured == 1)
  }

  object PrefData {
    def apply(pref: Pref): PrefData = PrefData(
      autoQueen = pref.autoQueen,
      autoThreefold = pref.autoThreefold,
      takeback = pref.takeback,
      clockTenths = pref.clockTenths.fold(1, 0),
      clockBar = pref.clockBar.fold(1, 0),
      clockSound = pref.clockSound.fold(1, 0),
      follow = pref.follow.fold(1, 0),
      highlight = pref.highlight.fold(1, 0),
      destination = pref.destination.fold(1, 0),
      coords = pref.coords,
      replay = pref.replay,
      blindfold = pref.blindfold,
      challenge = pref.challenge,
      premove = pref.premove.fold(1, 0),
      animation = pref.animation,
      submitMove = pref.submitMove,
      captured = pref.captured.fold(1, 0))
  }

  def prefOf(p: Pref): Form[PrefData] = pref fill PrefData(p)

  val miniPref = Form(mapping(
    "autoQueen" -> number.verifying(Pref.AutoQueen.choices.toMap contains _),
    "blindfold" -> number.verifying(Pref.Blindfold.choices.toMap contains _),
    "submitMove" -> number.verifying(Set(0, 1) contains _)
  )(MiniPrefData.apply)(MiniPrefData.unapply))

  case class MiniPrefData(
      autoQueen: Int,
      blindfold: Int,
      submitMove: Int) {
    def apply(pref: Pref) = pref.copy(
      autoQueen = autoQueen,
      blindfold = blindfold,
      submitMove = submitMove)
  }

  object MiniPrefData {
    def apply(pref: Pref): MiniPrefData = MiniPrefData(
      autoQueen = pref.autoQueen,
      blindfold = pref.blindfold,
      submitMove = pref.submitMove)
  }

  def miniPrefOf(p: Pref): Form[MiniPrefData] = miniPref fill MiniPrefData(p)

  val theme = Form(single(
    "theme" -> nonEmptyText.verifying(Theme contains _)
  ))

  val pieceSet = Form(single(
    "set" -> nonEmptyText.verifying(PieceSet contains _)
  ))

  val theme3d = Form(single(
    "theme" -> nonEmptyText.verifying(Theme3d contains _)
  ))

  val pieceSet3d = Form(single(
    "set" -> nonEmptyText.verifying(PieceSet3d contains _)
  ))

  val bg = Form(single(
    "bg" -> text.verifying(List("light", "dark") contains _)
  ))

  val is3d = Form(single(
    "is3d" -> text.verifying(List("true", "false") contains _)
  ))
}
