async function downloadModel(model, caseId, suffix, button) {
    const status = document.getElementById("modelDownloadStatus");
    const messages = translations[document.documentElement.lang] || translations.es;
    status.textContent = "";
    if (!caseId) {
        status.textContent = messages.selectModelForDownload;
        return;
    }
    const { protocol, hostname } = window.location;
    const local = protocol === "file:" || hostname === "localhost" || hostname === "127.0.0.1";
    const origin = local ? "http://127.0.0.1:9004" : "https://trajecta-pm.fapret.com";
    button.disabled = true;
    try {
        const url = origin + "/listdiscovers/download/" + model + "/" + encodeURIComponent(caseId);
        const response = await fetch(url);
        if (!response.ok || !response.headers.get("Content-Type")?.startsWith("application/octet-stream")) {
            status.textContent = messages.modelDownloadUnavailable;
            return;
        }
        const link = document.createElement("a");
        const objectUrl = URL.createObjectURL(await response.blob());
        link.href = objectUrl;
        link.download = caseId + suffix;
        document.body.append(link);
        link.click();
        link.remove();
        setTimeout(() => URL.revokeObjectURL(objectUrl), 1000);
    } catch {
        status.textContent = messages.modelDownloadUnavailable;
    } finally {
        button.disabled = false;
    }
}
