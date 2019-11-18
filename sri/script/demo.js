const superagent = require("superagent");
const readline = require("readline");
const snooze = ms => new Promise(resolve => setTimeout(resolve, ms));
const URL = "localhost:8080/api/dsr/access";
const EraseURL = "localhost:8080/api/dsr/erase";

const initData = {
  piType: "PHONE",
  piData: "4123131123",
  topology: {
    categories: [
      {
        val: "A",
        subCategories: [
          {
            val: "A1",
            subCategories: [
              {
                val: "A11"
              },
              {
                val: "A12"
              }
            ]
          }
        ]
      }
    ]
  }
};

function askQuestion(query) {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
  });
  console.log("");
  return new Promise(resolve =>
    rl.question(query, ans => {
      rl.close();
      resolve(ans);
    })
  );
}

const start = async () => {
  console.log(
    "\n============================= Sending data access request with PII data below: =============================\n"
  );
  console.log(JSON.stringify(initData, null, 2));
  console.log("");
  const ans = await askQuestion(
    "\n============================= Send request? =============================\n"
  );
  var callBackID = "";
  superagent
    .post(URL)
    .send(initData) // sends a JSON post body
    .end(async function(err, res) {
      callBackID = res.body.data;
      const pollURL = URL + "/" + callBackID;
      console.log("\n The respose body is: \n");
      console.log(res.body);
      console.log(pollURL);

      const ans2 = await askQuestion(
        "\n============================= Send polling request? =============================\n"
      );
      while (true) {
        console.log(
          "============================= poll job [" +
            callBackID +
            "] ============================="
        );
        const poll = await superagent.get(pollURL);
        if (poll.body.data !== "Processing") {
          console.log(
            "============================= poll result ============================="
          );
          console.log(JSON.stringify(JSON.parse(poll.body.data), null, 2));
          break;
        }
        await snooze(2000);
      }

      const ans3 = await askQuestion(
        "\n============================= Erase this user? =============================\n"
      );
      superagent
        .delete(EraseURL)
        .send(initData) // sends a JSON post body
        .end(async function(err, res) {
          const ans4 = await askQuestion(
            "\n============================= Erasure is done, query the same user again? =============================\n"
          );
          superagent
            .post(URL)
            .send(initData) // sends a JSON post body
            .end(async function(err, res) {
              console.log(
                "\n============================= Result: =============================\n"
              );
              console.log(res.body);
              console.log(
                "\n============================= Demo ends. Thank you! =============================\n"
              );
            });
        });
    });
};

start();
