/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.breathingspaceifstub.model

import java.time.LocalDate

import cats.syntax.option._
import play.api.libs.json.Json
import uk.gov.hmrc.breathingspaceifstub.repository.Individual

final case class IndividualDetail0(nino: String, dateOfBirth: Option[LocalDate], crnIndicator: Option[Int])

object IndividualDetail0 {
  val fields = "details(nino,dateOfBirth,cnrIndicator)"

  implicit val writes = Json.writes[IndividualDetail0]

  def apply(individual: Individual): IndividualDetail0 =
    individual.individualDetails.fold(IndividualDetail0(individual.nino, none, none)) { details =>
      IndividualDetail0(individual.nino, details.dateOfBirth, details.crnIndicator)
    }
}
