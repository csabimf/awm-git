# awm-git

An [Application Workflow Manager](https://www.microfocus.com/documentation/enterprise-developer/ed50/ED-Eclipse/GUID-ACF0482D-11B6-4629-AE9A-7B02B6C3958F.html) function package with a set of tool types for working with Git repositories.

Features:
* a couple of generic tools that can be used to model arbitrary Git commands
* convenience tools for the most common commands
* utilities for the easy handling of advanced features, such as sparse checkout
* EGit integration

Contributions are welcome!


## Installation

1. Download the update site from the [latest release](https://github.com/csabimf/awm-git/releases/latest).
2. Install as any other Eclipse plug-in, using *Help* → *Install New Software...*


## Provided Tool Types

* [Custom Command](#custom-command)
* [Custom Repository Command](#custom-repository-command)
* [Clone](#clone)
* [Checkout](#checkout)
* [Add](#add)
* [Commit](#commit)
* [Create Branch](#create-branch)
* [Pull](#pull)
* [Push](#push)
* [Config Get](#config-get)
* [Config Set](#config-set)
* [Set Sparse Checkout](#set-sparse-checkout)
* [Connect EGit](#connect-egit)


### Custom Command

#### Function

This tool can be used to model arbitrary Git commands that don't require an existing Git repository, e.g.:
* git clone
* git init
* git config --global


#### Parameters

| Name              | I/O | Type   | Description |
| ----------------- |:---:|:------:| ----------- |
| Parm&nbsp;[\*]    | I   | String | Every occurence is treated as a separate parameter to *git.exe*. |
| Messages&nbsp;[O] | O   | String | Output produced by *git.exe*. |


### Custom Repository Command

#### Function

This tool can be used to model arbitrary Git commands that require an existing Git repository, e.g.:
* add
* commit

#### Parameters

| Name                | I/O | Type   | Description |
| ------------------- |:---:|:------:| ----------- |
| Repository&nbsp;[M] | I   | String | Path to the working directory of a local Git repository. |
| Parm&nbsp;[\*]      | I   | String | Every occurence is treated as a separate parameter to *git.exe*. |
| Messages&nbsp;[O]   | O   | String | Output produced by *git.exe*. |


### Clone

#### Function

Performs a "smart" clone.

The option for no implicit checkout, for example, allows setting up sparse checkout first.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Remote&nbsp;URL&nbsp;[M]       | I   | String | URL of the remote repository to clone. |
| Local&nbsp;Path&nbsp;[M]       | I   | String | Path of the local directory to clone into. |
| Depth&nbsp;[D]                 | I   | String | Clone only the last *depth* commits. |
| Since&nbsp;[D]                 | I   | String | Clone only the commits since the give date in *yyyy-MM-dd* format. |
| Branch&nbsp;[O]                | I   | String | Point newly created HEAD to the given *branch* instead of the remote's HEAD. |
| Checkout&nbsp;[O]              | I   | String | Check out HEAD after the clone is complete. *true* or *false*. Default: *false*. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Checkout

#### Function

Performs a checkout.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Repository&nbsp;[M]            | I   | String | Path to the working directory of a local Git repository. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Add

#### Function

Adds files to the index.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Repository&nbsp;[M]            | I   | String | Path to the working directory of a local Git repository. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Commit

#### Function

Records changes to the repository.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Repository&nbsp;[M]            | I   | String | Path to the working directory of a local Git repository. |
| Message&nbsp;[M]               | I   | String | Commit message. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Create Branch

#### Function

Creates a new branch.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Repository&nbsp;[M]            | I   | String | Path to the working directory of a local Git repository. |
| Name&nbsp;[M]                  | I   | String | Name of the branch to create. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Pull

#### Function

Fetches from and integrates with another repository or a local branch.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Repository&nbsp;[M]            | I   | String | Path to the working directory of a local Git repository. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Push

#### Function

Updates remote refs along with associated objects.

#### Parameters

| Name                           | I/O | Type   | Description |
| ------------------------------ |:---:|:------:| ----------- |
| Repository&nbsp;[M]            | I   | String | Path to the working directory of a local Git repository. |
| Additional&nbsp;Parm&nbsp;[\*] | I   | String | Each occurrence is passed as a separate additional parameter to *git.exe*. |
| Messages&nbsp;[O]              | O   | String | Output produced by *git.exe*. |


### Config Get

#### Function

Helper tool type for getting repository options.

#### Parameters

| Name                | I/O | Type   | Description |
| ------------------- |:---:|:------:| ----------- |
| Repository&nbsp;[M] | I   | String | Path to the working directory of a local Git repository. |
| Name&nbsp;[\*]      | I   | String | The name of the option to get. |
| Value&nbsp;[\*]     | O   | String | The current value of the option. |
| Messages&nbsp;[O]   | O   | String | Output produced by *git.exe*. |


### Config Set

#### Function

Helper tool type for setting repository options.

#### Parameters

| Name                | I/O | Type   | Description |
| ------------------- |:---:|:------:| ----------- |
| Repository&nbsp;[M] | I   | String | Path to the working directory of a local Git repository. |
| Name&nbsp;[\*]      | I   | String | The name of the option to set. |
| Value&nbsp;[\*]     | I   | String | The new value of the option. |
| Messages&nbsp;[O]   | O   | String | Output produced by *git.exe*. |


### Set Sparse Checkout

#### Function

Helper tool type for populating *sparse-checkout* under the *.git/info* directory.

#### Parameters

| Name                | I/O | Type   | Description |
| ------------------- |:---:|:------:| ----------- |
| Repository&nbsp;[M] | I   | String | Path to the working directory of a local Git repository. |
| Path&nbsp;[\*]      | I   | String | Each path will be a separte line in the *sparse-checkout* file. |


### Connect EGit

#### Function

Enables EGit features on an Eclipse project with a Git repository inside.

#### Parameters

| Name                       | I/O | Type   | Description |
| -------------------------- |:---:|:------:| ----------- |
| Repository&nbsp;[M]        | I   | String | Path to the working directory of a local Git repository. |
| Project&nbsp;Name&nbsp;[M] | I   | String | The name of the project in the Eclipse workspace, which contains the Git repository. |

## License

See [LICENSE](LICENSE).
