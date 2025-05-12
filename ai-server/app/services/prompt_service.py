import re
from pathlib import Path
from typing import Any

import yaml


class PromptService:
    def __init__(self, prompt_dir: str = "prompts"):
        self.prompt_dir = Path(prompt_dir)

    def load_prompt(self, name: str = "diagnosis_prompt.yaml") -> dict[str, Any]:
        with open(self.prompt_dir / name, encoding="utf-8") as f:
            return yaml.safe_load(f)

    def build_prompt(self, prompt_data: dict[str, Any], inputs: dict) -> str:
        # replace {key} in the template with the corresponding value from inputs
        template_vars = re.findall(r"{(\w+)}", prompt_data["patient_data_template"])

        safe_inputs = {
            key: ("" if inputs.get(key) is None else inputs.get(key, ""))
            for key in template_vars
        }

        return "\n".join(
            [
                prompt_data["role"],
                prompt_data["task"].format(
                    disease_list=", ".join(prompt_data["disease_list"])
                ),
                prompt_data["patient_data_template"].format(**safe_inputs),
                prompt_data["output_format"],
            ]
        )
