| Point           | Cristian’s Algorithm    | Berkeley Algorithm          |
| --------------- | ----------------------- | --------------------------- |
| Who is correct? | Server clock is correct | No clock is assumed correct |
| Method          | Client asks server time | Server collects all times   |
| Adjustment      | Client adjusts itself   | Server adjusts everyone     |
| Use case        | Internet time sync      | Local distributed systems   |
| Complexity      | Simple                  | Slightly complex            |
Cristian’s

👉 Like checking Google time

Your watch = wrong

Google time = correct

You ask Google and fix your watch

✅ Only client changes

🔹 Berkeley

👉 Like class teacher correcting all students’ watches

Teacher collects all times

Finds average

Tells everyone to adjust

✅ Everyone changes (including server)
