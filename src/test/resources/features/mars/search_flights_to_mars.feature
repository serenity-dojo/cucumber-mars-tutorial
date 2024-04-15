Feature: Search flights to Mars

  As a MarsAir Sales Director (Mark)
  I want potential customers to be able to search for flights to Mars
  So that they see what trips are available

  Acceptance criteria

  - There should be ‘departure’ and ‘return’ fields on a search form.
  - Flights leave every six months, in July and December, both ways.
  - Trips for the next two years should be searchable.
  - If there are seats, display “Seats available! Call 0800 MARSAIR to book!”
  - If there are no seats, display “Sorry, there are no more seats available.”

  Martin is a potential customer who wants to book a flight to Mars.

  Rule: There should be ‘departure’ and ‘return’ fields on a search form.
    Example: Martin wants to book a flight to Mars
      Given Martin is on the MarsAir homepage
      When he wants to look for available flights
      Then he should be able to specify the departure and return months that he wants to travel

  Rule: Flights leave every six months for the next two years, in July and December, both ways.
    Example: Departure flights leave in July and December for the next two years
      Given Martin is on the MarsAir homepage
      When he wants to look for available flights
      Then he should see the following departure months:
        | July                          |
        | December                      |
        | July (next year)              |
        | December (next year)          |
        | July (two years from now)     |
        | December (two years from now) |
      And he should see the following return months:
        | July                          |
        | December                      |
        | July (next year)              |
        | December (next year)          |
        | July (two years from now)     |
        | December (two years from now) |

  Rule: If there are seats, display “Seats available! Call 0800 MARSAIR to book!” otherwise display “Sorry, there are no more seats available.”
    Scenario Outline: Martin wants to book a flight to Mars
      Given Martin is on the MarsAir homepage
      When he looks for available flights for the following dates:
        | Departure   | Return   |
        | <Departure> | <Return> |
      Then he should be told "<Expected Result>"
      Examples: Seats available
        | Departure | Return                        | Expected Result                   |
        | July      | December (two years from now) | Call now on 0800 MARSAIR to book! |

      Examples: No more seats left
        | Departure | Return               | Expected Result                           |
        | July      | July (next year)     | Sorry, there are no more seats available. |
        | December  | December (next year) | Sorry, there are no more seats available. |

      Examples: Invalid return dates
        | Departure | Return   | Expected Result                                                 |
        | July      | December | Unfortunately, this schedule is not possible. Please try again. |
