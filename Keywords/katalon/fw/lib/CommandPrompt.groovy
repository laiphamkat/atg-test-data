package katalon.fw.lib

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.util.concurrent.TimeUnit

import org.apache.commons.lang3.SystemUtils

import com.kms.katalon.core.util.KeywordUtil

public class CommandPrompt {
	Process proc
	List<String> commands = []

	public CommandPrompt sleep(long timeOut) {
		Thread.sleep(timeOut * 1000)
		return this
	}

	public CommandPrompt printLogs() {
		BufferedReader stdInput = new BufferedReader(new
				InputStreamReader(proc.getInputStream()))

		BufferedReader stdError = new BufferedReader(new
				InputStreamReader(proc.getErrorStream()))

		// Read the output from the command
		String s = ''
		String full = ''
		while ((s = stdInput.readLine()) != null) {
			full = full + s + System.lineSeparator()
		}
		// Read any errors from the attempted command
		while ((s = stdError.readLine()) != null) {
			full = full + s + System.lineSeparator()
		}

		println full
		return this
	}

	public void executeCommand(String[] commands) {
		KeywordUtil.logInfo(commands[2])

		proc = Runtime.getRuntime().exec(commands)
		this.commands = []
	}

	public void executeWindowsCommand(String command) {
		executeCommand((String[]) ["cmd", "/c", command])
	}

	public void executeLinuxMacOSCommand(String command) {
		executeCommand((String[]) ["bash", "-c", command])
	}

	public CommandPrompt executeCommand() {
		if (SystemUtils.IS_OS_WINDOWS) {
			executeWindowsCommand(commands.join(' && '))
		} else {
			executeLinuxMacOSCommand(commands.join('; '))
		}
		return this
	}

	public CommandPrompt closeTestCloudTunnels() {
		proc.destroy()
		if (SystemUtils.IS_OS_WINDOWS) {
			commands.add('taskkill /IM "kt.exe" /F')
		} else {
			commands.add("pkill -f kt")
		}
		executeCommand()
		printLogs()
		return this
	}

	public CommandPrompt close() {
		proc.destroy()
		return this
	}

	public CommandPrompt open() {
		return this
	}

	public CommandPrompt startInputCommand() {
		return this
	}

	public CommandPrompt pasteFromClipboard() {
		commands.add((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor))
		return this
	}

	public CommandPrompt input(String command) {
		commands.add(command)
		return this
	}

	public CommandPrompt cd(String location) {
		commands.add("cd ${location}")
		return this
	}

	public CommandPrompt createFolder(String folderName) {
		commands.add("mkdir ${folderName}")
		return this
	}

	public CommandPrompt moveFile(String fileName, String destination) {
		if (SystemUtils.IS_OS_WINDOWS) {
			commands.add("move ${fileName} ${destination}")
		} else {
			commands.add("mv ${fileName} ${destination}")
		}
		return this
	}

	public String getDownloadedTunnelFileName() {
		if (SystemUtils.IS_OS_WINDOWS) {
			if (System.getenv("ProgramFiles(x86)") != null) {
				return 'kt-win-x64-v1.3.0.zip'
			} else {
				return 'kt-win-x86-v1.3.0.zip'
			}
		} else if (SystemUtils.IS_OS_LINUX) {
			return 'kt-linux-x64-v1.3.0.zip'
		} else {
			return 'kt-macos-x64-v1.3.0.dmg'
		}
	}

	public CommandPrompt moveDownloadedTunnelFileTo(String destination) {
		moveFile(getDownloadedTunnelFileName(), destination)
		return this
	}

	public CommandPrompt removeFolder(String directory) {
		if (SystemUtils.IS_OS_WINDOWS) {
			commands.add("rmdir /Q /s ${directory}")
		} else {
			commands.add("rm -r ${directory}")
		}
		return this
	}

	public CommandPrompt unzip(String fileName) {
		if (SystemUtils.IS_OS_WINDOWS) {
			commands.add("tar -xf ${fileName}")
		} else {
			commands.add("unzip ${fileName}")
		}
		return this
	}

	public CommandPrompt unzipDownloadedTunnelFile() {
		unzip(getDownloadedTunnelFileName())
		return this
	}
}
