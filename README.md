# Embabel – Getting Started
> A minimal “hello‑world” demo of the Embabel AI‑orchestration framework  
> Repository prepared for conference / kickoff session

## 🧩 What is Embabel?
Embabel is a lightweight orchestration framework designed to simplify building *orchestrated AI systems* — where multiple specialized agents (retrievers, reasoners, executors) collaborate via connectors, pipelines and a central runtime.  
It enables you to compose LLMs, tools, data‑sources and workflows in a structured way.

## 🚀 Getting Started
This repository shows how to quickly spin up a demo pipeline using Embabel.  
It includes two example sub‑projects:  
- `embabel‑shell‑demo` — run from terminal/CLI  
- `embabel‑rest‑demo` — run as a REST service  
Both share the core orchestration concepts of Embabel.

## 📁 Repository Structure
```
/embabel-getting‑started
  ├─ embabel‑shell‑demo/       # CLI demo: simple orchestrated flow
  ├─ embabel‑rest‑demo/        # REST‑based demo: HTTP endpoint, agent orchestration
  └─ other config / .idea files etc.
```

## ✅ Demo Features
- Define agents with specific roles (e.g., search, summarise, decide)  
- Use connectors to interface with a model or data source  
- Configure a pipeline that chains or coordinates agents  
- Run the orchestration via Embabel Core runtime  
- Observe how individual specialized agents collaborate rather than a monolithic model

## 🔧 Setup & Run
1. Clone the repository:  
   ```bash
   git clone https://github.com/m-tilab/embabel-getting-started.git
   cd embabel-getting-started
   ```  
2. For the shell demo:  
   ```bash
   cd embabel-shell-demo
   mvn clean install
   mvn exec:java
   ```  
3. For the REST demo:  
   ```bash
   cd embabel-rest-demo
   mvn clean install
   mvn spring-boot:run
   ```  
4. Modify configuration (e.g., model connector, data source) in `application.yml` or equivalent config.  
5. Trigger the pipeline through CLI or REST endpoint and observe the flow of agents, logs, results.

## 📚 Core Concepts (Glossary)
- **Agent**: a specialized unit of intelligence performing retrieval, reasoning, execution.  
- **Connector**: interface bridging Embabel agents to external systems (models, APIs, data‑stores).  
- **Pipeline**: defines how agents collaborate (sequence, parallel, conditional).  
- **Core Runtime**: Embabel’s orchestration engine managing agents, context, message passing.

## 🎯 Why Use Embabel?
Compared to more monolithic AI systems, an orchestrated approach brings:  
- greater **modularity** (swap or add agents easily)  
- enhanced **scalability** (distribute tasks across agents)  
- improved **adaptability** (specialised agents evolve independently)  
- better **coordination** (agents collaborate rather than operate in isolation)  

## 📩 Contributing
Contributions, feedback and demonstrations are welcome!  
If you’d like to:  
- Add a new demo (e.g., conversational UI, knowledge base integration)  
- Extend a connector (e.g., new LLM provider, new tool)  
- Improve documentation or slide deck  
Please open a pull request or issue.

## 📝 License
Specify your license here (e.g., MIT, Apache 2.0) — or note “proprietary / internal use only” if this is intended for internal use.
